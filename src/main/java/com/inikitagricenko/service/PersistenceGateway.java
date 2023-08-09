package com.inikitagricenko.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.inikitagricenko.model.PersonRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersistenceGateway {

	private AmazonDynamoDB amazonDynamoDB;

	@Value("${db.table}")
	private String DYNAMODB_TABLE_NAME;
	@Value("${aws.region:eu-central-1}")
	private String REGION;

	public PersistenceGateway() {
		init();
	}

	void init() {
		this.amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withRegion(Regions.fromName(REGION))
				.build();
	}

	public void save(PersonRequest personRequest) {
		Map<String, AttributeValue> attributeValues = new HashMap<>(getInsertValues(personRequest));

		amazonDynamoDB.putItem(DYNAMODB_TABLE_NAME, attributeValues);
	}

	public List<PersonRequest> getAll() {
		DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
				.withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(DYNAMODB_TABLE_NAME))
				.build();

		DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		scanExpression.setLimit(25);

		return mapper.scan(PersonRequest.class, scanExpression);
	}

	public PersonRequest get(Long id) {
		DynamoDB dynamoDb = new DynamoDB(amazonDynamoDB);
		String json = dynamoDb.getTable(DYNAMODB_TABLE_NAME).getItem("id", id).toJSON();
		return new PersonRequest(json);
	}

	private Map<String, AttributeValue> getInsertValues(PersonRequest data) {
		List<Field> fieldsToInsert = Arrays.stream(data.getClass().getDeclaredFields()).toList();
		return fieldsToInsert.stream().collect(Collectors.toMap(Field::getName, field -> {
			Object value = null;
			try {
				value = field.get(field.getType());
			} catch (IllegalAccessException ignore) { }
			return new AttributeValue(String.valueOf(value));
		}));
	}




}
