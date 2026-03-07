FROM eclipse-temurin:21-jdk

WORKDIR /app

# Install GUI/X11 libraries needed by JavaFX
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip \
    && rm -rf /var/lib/apt/lists/*

# Download and extract JavaFX SDK 21 for Linux
RUN mkdir -p /javafx-sdk \
    && wget -O javafx.zip https://download2.gluonhq.com/openjfx/21/openjfx-21_linux-x64_bin-sdk.zip \
    && unzip javafx.zip -d /javafx-sdk \
    && mv /javafx-sdk/javafx-sdk-21/lib /javafx-sdk/lib \
    && rm -rf /javafx-sdk/javafx-sdk-21 javafx.zip

# Copy the fat JAR built by Maven Shade Plugin
COPY target/averagespeed.jar app.jar

# Forward display to Windows via Xming
ENV DISPLAY=host.docker.internal:0.0

# DB_HOST defaults to "db" (Docker Compose service name); override at runtime if needed
ENV DB_HOST=db

# Run the JavaFX app using the JavaFX SDK module path
CMD ["java", "--module-path", "/javafx-sdk/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "app.jar"]
