from json import dumps
from kafka import KafkaProducer
from kafka.errors import KafkaError

# pip install kafka-python
producer = KafkaProducer(
    bootstrap_servers=['localhost:9092'],
    value_serializer=lambda x: dumps(x).encode('utf-8'))

data = {'firstName':'test','lastName':'test','document':'1234567890','email':'test@test.com'}
#data = {'document':'0987654321'}
future = producer.send('queueing.example.customer.created', value=data)

try:
    record_metadata = future.get(timeout=10)
except KafkaError:
    log.exception()
    pass
