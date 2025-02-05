To deploy to EC2:

Build your application: ./gradlew build
Upload the JAR to EC2: scp -i your-key.pem build/libs/job-application-bot-*.jar ec2-user@your-ec2-ip:/opt/job-application-bot/
Upload and run the setup script: scp -i your-key.pem setup-ec2.sh ec2-user@your-ec2-ip:~/ && ssh -i your-key.pem ec2-user@your-ec2-ip 'chmod +x setup-ec2.sh && ./setup-ec2.sh'