package pl.droidcon.app.helper;


import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public interface Mapper<ApiObject, DatabaseObject extends RealmObject> {

    DatabaseObject map(ApiObject apiObject);

    RealmList<DatabaseObject> mapList(List<ApiObject> apiObjects);

    RealmList<DatabaseObject> matchFromApi(List<DatabaseObject> databaseObjects, List<Integer> ids);

    List<ApiObject> fromDBList(List<DatabaseObject> databaseObjects);
}
