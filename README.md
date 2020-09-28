# multistreamjoin
Multistream Join Job - Connects streams currency Purchase & Payment to create a Match which is called Deal. Late / unmatched event is pushed to late arrival topics. See code documentation for better understanding for match working.  


## Purchase 
```
{
  "transactionID": "EN123",
  "clientId": "1232121",
  "channel": "online",
  "purchaseCurrency": "AED",
  "saleCurrency": "EUR",
  "purchaseAmount": "989",
  "saleAmount": "238",
  "rate": "4.16",
  "rateCode": "CUS",
  "buysell": "b",
  "transactionDate": "2020-06-07T00:55:01.258+04:00"
}
```

## Payment

```
{
  "transactionID": "EN123",
  "clientId": "1232121",
  "accountNumber": "1341232",
  "amount": "989",
  "status": "success",
  "referenceNumber": "E32e3e",
  "transactionDate": "2020-06-07T00:55:10.258+04:00"
}
```

## Deal - Purchase + Payment

```
{
    "transactionID": "EN123",
    "transactionDate": "2019-12-T12:12:22"
    "channel": "online",
    "cif": "1232121"
    "saleCurrency": "EUR",
    "purchaseCurrency": "AED",
    "saleAmount": "238"
    "purchaseAmount": "989",
    "rate": "4.16",
    "rateCode": "CUS"
    "buysell": "b",
    "status" : "success"
    "referenceNumber" : "E32e3e"
}
```

## Create topics

```
confluent local start

kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic purchase

kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic payment

kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic purchase-dql

kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic payment-dql

kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic deal

kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic unmatched-purchase

kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic unmatched-payment

```

## Consume events 

```

kafka-console-consumer --bootstrap-server localhost:9092 --topic deal

kafka-console-consumer --bootstrap-server localhost:9092 --topic purchase-dql

kafka-console-consumer --bootstrap-server localhost:9092 --topic payment-dql

```

## Push events for testing matches

```

mvn clean package

/opt/fink/start-cluster.sh

/opt/flink/flink run target/singlestream-aggregates-0.1.jar &

Execute io.kubesure.multistream.TestDealMatcher in IDE 
```
