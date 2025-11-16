package com.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public TicketDAO() {
        // Connect to MongoDB with authentication
        MongoCredential credential = MongoCredential.createCredential("root", "admin", "1070..".toCharArray());
        mongoClient = MongoClients.create(
            MongoClientSettings.builder()
                .applyToClusterSettings(builder -> 
                    builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
                .credential(credential)
                .build()
        );
        database = mongoClient.getDatabase("ticketdb");
        collection = database.getCollection("tickets");
    }

    public void createTicket(Ticket ticket) {
        Document doc = new Document("title", ticket.getTitle())
                .append("description", ticket.getDescription())
                .append("status", ticket.getStatus());
        collection.insertOne(doc);
        ticket.setId(doc.getObjectId("_id"));
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (Document doc : collection.find()) {
            Ticket ticket = new Ticket();
            ticket.setId(doc.getObjectId("_id"));
            ticket.setTitle(doc.getString("title"));
            ticket.setDescription(doc.getString("description"));
            ticket.setStatus(doc.getString("status"));
            tickets.add(ticket);
        }
        return tickets;
    }

    public Ticket getTicketById(String id) {
        Document doc = collection.find(new Document("_id", new ObjectId(id))).first();
        if (doc != null) {
            Ticket ticket = new Ticket();
            ticket.setId(doc.getObjectId("_id"));
            ticket.setTitle(doc.getString("title"));
            ticket.setDescription(doc.getString("description"));
            ticket.setStatus(doc.getString("status"));
            return ticket;
        }
        return null;
    }

    public void updateTicket(Ticket ticket) {
        Document filter = new Document("_id", ticket.getId());
        Document update = new Document("$set", new Document("title", ticket.getTitle())
                .append("description", ticket.getDescription())
                .append("status", ticket.getStatus()));
        collection.updateOne(filter, update);
    }

    public void deleteTicket(String id) {
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    public void close() {
        mongoClient.close();
    }
}