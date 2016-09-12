package com.websocket.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.websocket.app.model.Message;

@Component
public class MessageMongoAccessor {

	@Autowired
	MongoOperations mongoOperation;

	/*
	 * .orOperator( Criteria.where("senderUsername").is(receiverUsername)
	 * .and("receiverUsername").is(senderUsername) )
	 */
	public List<Message> findBySenderNameAndReceiverName(String senderUsername,
			String receiverUsername) {
		Query q = new Query();
		q.addCriteria(Criteria
				.where("senderUsername")
				.exists(true)
				.orOperator(Criteria.where("senderUsername").is(senderUsername)
							.and("receiverUsername").is(receiverUsername),
							Criteria.where("senderUsername").is(receiverUsername)
							.and("receiverUsername").is(senderUsername))
				);
		q.with(new Sort(Sort.Direction.ASC, "pubDate"));
		return mongoOperation.find(q, Message.class);
	}

	public long countBySenderNameAndReceiverName(String senderUsername,
			String receiverUsername) {
		Query q = new Query();
		q.addCriteria(Criteria
				.where("senderUsername")
				.exists(true)
				.orOperator(Criteria.where("senderUsername").is(senderUsername)
							.and("receiverUsername").is(receiverUsername),
							Criteria.where("senderUsername").is(receiverUsername)
							.and("receiverUsername").is(senderUsername))
				);
		q.with(new Sort(Sort.Direction.ASC, "pubDate"));
		return mongoOperation.count(q, Message.class);
	}

	public long countBySenderNameAndReceiverNameAndPubDateGreaterThan(
			String senderUsername, String receiverUsername, Date lastReadDate) {
		Query q = new Query();
		q.addCriteria(Criteria
				.where("pubDate")
				.gt(lastReadDate)
				.orOperator(Criteria.where("senderUsername").is(senderUsername)
						.and("receiverUsername").is(receiverUsername),
				Criteria.where("senderUsername").is(receiverUsername)
						.and("receiverUsername").is(senderUsername))
				);
		q.with(new Sort(Sort.Direction.ASC, "pubDate"));
		return mongoOperation.count(q, Message.class);
	}

}
