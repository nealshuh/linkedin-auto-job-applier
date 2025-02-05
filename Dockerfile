FROM amazoncorretto:17-alpine

# Install Chrome and dependencies
RUN apk add --no-cache \
    chromium \
    chromium-chromedriver \
    nss \
    freetype \
    freetype-dev \
    harfbuzz \
    ca-certificates \
    ttf-freefont \
    nodejs \
    npm

# Set Chrome environment variables
ENV CHROME_BIN=/usr/bin/chromium-browser \
    CHROME_PATH=/usr/lib/chromium/ \
    CHROMIUM_FLAGS="--headless --no-sandbox --disable-dev-shm-usage"

# Create app directory
WORKDIR /app

# Copy application jar
COPY build/libs/job-application-bot-*.jar app.jar

# Copy any additional resources
COPY src/main/resources/application.yml application.yml

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]