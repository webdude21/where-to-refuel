package where.to.refuel.server.model.repository;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.Success;
import io.reactivex.Flowable;
import org.bson.Document;
import where.to.refuel.server.model.UserLog;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserLogRepository {

  private static final String USER_INFO_COLLECTION = "user_info_log";
  private static final String DATABASE_NAME = "heroku_5kk33l34";
  private final MongoClient mongoClient;

  @Inject
  public UserLogRepository(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public Flowable<Success> logUserInfo(UserLog userLog) {
    var collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(USER_INFO_COLLECTION);
    Document document = new Document();
    document.put("requestedOn", userLog.getRequestedOn());
    document.put("location", userLog.getLocation());
    document.put("fuelType", userLog.getFuelType());
    document.put("ipAddress", userLog.getIpAddress());
    return Flowable.fromPublisher(collection.insertOne(document));
  }
}