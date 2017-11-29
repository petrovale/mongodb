package course;

import com.mongodb.*;

public class Task7 {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        DB db = mongoClient.getDB("final7");
        int i = 0;
        DBCollection album = db.getCollection("albums");
        DBCollection image = db.getCollection("images");
        DBCursor dbCursor = image.find();
        dbCursor.next();

        while(dbCursor.hasNext()) {
            Object id = dbCursor.curr().get("_id");
            DBCursor curAlbum = album.find(new BasicDBObject("images", id));
            if(!curAlbum.hasNext()) {
                image.remove(new BasicDBObject("_id", id));
            }
            dbCursor.next();
        }
    }
}
