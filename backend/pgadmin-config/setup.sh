#!/bin/sh

# Create the directory for servers.json
mkdir -p /pgadmin4/

# Generate the servers.json configuration file using environment variables
cat << EOF > /pgadmin4/servers.json
{
    "Servers": {
        "1": {
            "Name": "admin-ease",
            "Group": "Servers",
            "Host": "database",
            "Port": 5432,
            "MaintenanceDB": "${POSTGRES_DB}",
            "Username": "${POSTGRES_USER}",
            "Password": "${POSTGRES_PASSWORD}",
            "SSLMode": "prefer"
        }
    }
}
EOF

# Set the environment variable for pgAdmin to load the servers.json file
export PGADMIN_CONFIG_SERVER_JSON_FILE=/pgadmin4/servers.json

# Execute the original entrypoint script
exec /entrypoint.sh "$@"