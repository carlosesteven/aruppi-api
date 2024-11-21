#!/bin/sh

# Check if MONGO_CONNECTION_STRING is defined
if [ -z "$MONGO_CONNECTION_STRING" ]; then
  echo "ERROR: The environment variable for the database, MONGO_CONNECTION_STRING is not defined."
  exit 1
fi

# Verificar si MONGO_DATABASE_NAME está definido
if [ -z "$MONGO_DATABASE_NAME" ]; then
  echo "WARNING: The environment variable for the database, MONGO_DATABASE_NAME, is not defined. It is using ‘mongodb’ as the default value."
  MONGO_DATABASE_NAME="mongodb"
fi


# Generate the application.yaml file
cat <<EOF > /app/application.yaml
ktor:
  application:
    modules:
      - com.jeluchu.ApplicationKt.module
  deployment:
    port: 8080
    host: "0.0.0.0"

db:
  mongo:
    connectionStrings: "${MONGO_CONNECTION_STRING}"
    database:
      name: "${MONGO_DATABASE_NAME:-mongodb}"
EOF

# Run the application with the specified configuration
exec java -Dconfig.file=/app/application.yaml -jar /app/app.jar