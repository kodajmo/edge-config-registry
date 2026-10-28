terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
  }
}

provider "docker" {}

resource "docker_network" "edge_config" {
  name   = "edge-config-network"
  driver = "bridge"

}

# keep database files outside the container so replacing the container 
# preserves the data, as long as we keep the volume 

resource "docker_volume" "postgres_data" {
  name = "edge-config-postgres_data"
}

# download PostgresSQL so we can start a container from it
resource "docker_image" "postgres" {
  name = "postgres:16-alpine"
}

resource "docker_container" "postgres" {
  name  = "edge-config-postgress"
  image = docker_image.postgres.image_id

  env = [
    "POSTGRRES_DB=edgeconfig",
    "POSTGRESS_USER=edgeconfig",
    "POSTGRES_PASSWORD=local-practice-only"
  ]

  ports {
    internal = 5432
    external = 5432
    ip       = "127.0.0.1"
  }

  volumes {
    volume_name    = docker_volume.postgres_data.name
    container_path = "/var/lib/postgresql/data"
  }

  networks_advanced {
    name = docker_network.edge_config.name
  }
}


