package where.to.refuel.server.model.repository;

import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import org.bson.Document;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.UserLog;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Singleton
public class UserLogRepository {

  private static final String REQUESTED_ON = "requestedOn";
  private static final String LOCATION = "location";
  private static final String FUEL_TYPE = "fuelType";
  private static final String IP_ADDRESS = "ipAddress";
  private static final String LATITUDE = "latitude";
  private static final String LONGITUDE = "longitude";
  private static final String USER_INFO_COLLECTION = "user_info_log";
  private static final String DATABASE_NAME = "heroku_5kk33l34";
  private final MongoClient mongoClient;

  @Inject
  public UserLogRepository(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public Flowable<InsertOneResult> logUserInfo(UserLog userLog) {
    var collection = getUserCollection();
    Document document = new Document();
    document.put(REQUESTED_ON, userLog.getRequestedOn());
    document.put(LOCATION, userLog.getLocation());
    document.put(FUEL_TYPE, userLog.getFuelType());
    document.put(IP_ADDRESS, userLog.getIpAddress());
    return Flowable.fromPublisher(collection.insertOne(document));
  }

  public Flowable<UserLog> findAllUserLogs() {
    return Flowable.fromPublisher(getUserCollection().find()).map(this::from);
  }

  private MongoCollection<Document> getUserCollection() {
    return mongoClient.getDatabase(DATABASE_NAME).getCollection(USER_INFO_COLLECTION);
  }

  private UserLog from(Document document) {
    var locationDocument = document.get(LOCATION, Document.class);
    var location = Coordinates.of(locationDocument.getDouble(LATITUDE), locationDocument.getDouble(LONGITUDE));
    var requestedOn = document.get(REQUESTED_ON, Date.class);
    var requestedOnAsLocalDate = LocalDateTime.ofInstant(requestedOn.toInstant(), ZoneId.systemDefault());
    return UserLog.of(location, document.getString(FUEL_TYPE), document.getString(IP_ADDRESS), requestedOnAsLocalDate);
  }
}
