# multistreamjoin
Multistream Join - Flink

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

## Push events for matching

```
kafka-console-producer --broker-list localhost:9092 --topic purchase

kafka-console-producer --broker-list localhost:9092 --topic payment
```


```

kafka-console-consumer --bootstrap-server localhost:9092 --topic deal

kafka-console-consumer --bootstrap-server localhost:9092 --topic purchase-dql

kafka-console-consumer --bootstrap-server localhost:9092 --topic payment-dql

```

Purchase - Initiated by any channel

```
{"transactionID": "EN123","clientId": "1232121","channel": "online","purchaseCurrency": "AED","saleCurrency": "EUR","purchaseAmount": "989","saleAmount": "238","rate":"4.16","rateCode": "CUS","buysell": "b","transactionDate": "2020-06-07T00:55:01.258+04:00"}
```

Transfer - Done by CBS

```
{"transactionID": "EN123","clientId": "1232121","accountNumber": "1341232","status" : "success","referenceNumber": "E32e3e","transactionDate": "2020-06-07T00:55:10.258+04:00"}
```

Deal - Purchase + Payment

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