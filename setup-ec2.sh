#!/bin/bash

# Update system
sudo yum update -y

# Install Java 17
sudo yum install -y java-17-amazon-corretto

# Install Chrome and ChromeDriver
sudo curl https://intoli.com/install-google-chrome.sh | bash
sudo yum install -y chromium chromedriver

# Create application directory
sudo mkdir -p /opt/job-application-bot
sudo chown ec2-user:ec2-user /opt/job-application-bot

# Setup environment variables
echo "export OPENAI_API_KEY='your_key_here'" >> ~/.bashrc
echo "export CLAUDE_API_KEY='your_key_here'" >> ~/.bashrc
source ~/.bashrc

# Create log directory
sudo mkdir -p /var/log/job-application-bot
sudo chown ec2-user:ec2-user /var/log/job-application-bot

# Copy systemd service file
sudo cp job-application-bot.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable job-application-bot
sudo systemctl start job-application-bot