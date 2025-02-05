# Deployment Instructions

## Deploy to EC2

1. Build your application:
```bash
./gradlew build
```

2. Upload the JAR to EC2:
```bash
scp -i your-key.pem build/libs/job-application-bot-*.jar ec2-user@your-ec2-ip:/opt/job-application-bot/
```

3. Upload and run the setup script:
```bash
scp -i your-key.pem setup-ec2.sh ec2-user@your-ec2-ip:~/ && ssh -i your-key.pem ec2-user@your-ec2-ip 'chmod +x setup-ec2.sh && ./setup-ec2.sh'
```

## Demo
![Demo](assets/demo.MOV)