package pl.droidcon.app.rx;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class BinderUtil {

    private static final String TAG = BinderUtil.class.getSimpleName();

    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public BinderUtil() {
    }

    public void clear() {
        compositeSubscription.clear();
    }

    public void unbind(Subscription subscription) {
        compositeSubscription.remove(subscription);
    }

    public <U> Subscription bindProperty(final Observable<U> observable,
                                         final Action1<U> setter,
                                         final Action0 error) {
        Subscription subscription = subscribeSetter(observable, setter, error);
        compositeSubscription.add(subscription);
        return subscription;
    }

    private <U> Subscription subscribeSetter(final Observable<U> observable,
                                             final Action1<U> setter,
                                             final Action0 error) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SetterSubscriber<>(setter, error));
    }

    private static class SetterSubscriber<U> extends Subscriber<U> {

        private final Action1<U> setter;
        private final Action0 error;

        public SetterSubscriber(final Action1<U> setter,
                                final Action0 error) {
            this.setter = setter;
            this.error = error;
        }

        @Override
        public void onCompleted() {
            Log.d(TAG,"onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            Log.e(TAG, "onError", e);
            if (error != null) {
                error.call();
            }
        }

        @Override
        public void onNext(U u) {
            Log.d(TAG,"onNext="+u);
            if (setter != null) {
                setter.call(u);
            }
        }
    }
}
