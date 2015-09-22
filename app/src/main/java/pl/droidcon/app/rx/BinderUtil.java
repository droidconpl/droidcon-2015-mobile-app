package pl.droidcon.app.rx;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class BinderUtil {

    private final String tag;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public BinderUtil(Object target) {
        this.tag = target.getClass().getSimpleName();
    }

    public void clear() {
        compositeSubscription.clear();
    }

    public <U> void bindProperty(final Observable<U> observable,
                                 final Action1<U> setter) {
        compositeSubscription.add(
                subscribeSetter(observable, setter, tag));
    }

    private <U> Subscription subscribeSetter(final Observable<U> observable,
                                                    final Action1<U> setter,
                                                    final String tag) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SetterSubscriber<>(setter, tag));
    }

    private static class SetterSubscriber<U> extends Subscriber<U> {
        private final static String TAG = SetterSubscriber.class.getCanonicalName();

        private final Action1<U> setter;
        private final String tag;

        public SetterSubscriber(final Action1<U> setter,
                                final String tag) {
            this.setter = setter;
            this.tag = tag;
        }

        @Override
        public void onCompleted() {
            Log.v(TAG, tag + "." + "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, tag + "." + "onError", e);
        }

        @Override
        public void onNext(U u) {
            setter.call(u);
        }
    }
}
