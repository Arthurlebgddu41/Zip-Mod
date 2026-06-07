#!/bin/bash

# Installation script for Zip-Mod
# This script will:
# 1. Download gradle wrapper
# 2. Make it executable
# 3. Build the mod

set -e

echo "========================================="
echo "Zip-Mod Build Script"
echo "========================================="

# Check if we're in the right directory
if [ ! -f "build.gradle" ]; then
    echo "Error: build.gradle not found!"
    echo "Please run this script from the Zip-Mod directory"
    exit 1
fi

echo "✓ Found build.gradle"

# Create gradle wrapper directory if it doesn't exist
mkdir -p gradle/wrapper

# Download gradle wrapper jar
echo "Downloading Gradle wrapper..."
if [ ! -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    curl -L https://github.com/gradle/gradle/releases/download/v8.6.0/gradle-8.6-wrapper.jar -o gradle/wrapper/gradle-wrapper.jar
    echo "✓ Gradle wrapper downloaded"
else
    echo "✓ Gradle wrapper already exists"
fi

# Download gradle wrapper properties if needed
if [ ! -f "gradle/wrapper/gradle-wrapper.properties" ]; then
    cat > gradle/wrapper/gradle-wrapper.properties << 'EOF'
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.6-all.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
EOF
    echo "✓ Created gradle-wrapper.properties"
fi

# Make gradlew executable if it exists
if [ -f "gradlew" ]; then
    chmod +x gradlew
    echo "✓ gradlew is now executable"
fi

# Now build
echo ""
echo "Starting build..."
echo "This may take 5-10 minutes on first run..."
echo ""

if [ -f "gradlew" ]; then
    ./gradlew build
else
    # Fallback to system gradle
    gradle build
fi

echo ""
echo "========================================="
echo "✓ Build successful!"
echo "JAR file: build/libs/zipmod-1.0.0.jar"
echo "========================================="
