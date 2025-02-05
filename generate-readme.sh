#!/bin/bash

# Create or overwrite README.md
cat > README.md << 'EOF'
# Deployment Instructions

## Deploy to EC2

1. Build your application:

```
./gradlew build
```

2. Upload the JAR to EC2:

```
scp -i your-key.pem build/libs/job-application-bot-*.jar ec2-user@your-ec2-ip:/opt/job-application-bot/
```

3. Upload and run the setup script:

```
scp -i your-key.pem setup-ec2.sh ec2-user@your-ec2-ip:~/ && ssh -i your-key.pem ec2-user@your-ec2-ip 'chmod +x setup-ec2.sh && ./setup-ec2.sh'
```

## Demo

![Demo](./demo.MOV)
EOF

# Make the script executable
chmod +x generate-readme.sh

# Print success message
echo "README.md has been generated successfully!"