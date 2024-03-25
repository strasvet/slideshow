#!/usr/bin/env bash
CURR_IP=$(hostname -I | awk '{print $1}')
base_curr_dir=$(dirname $0)
source $base_curr_dir/../common-env.sh
source $base_curr_dir/../version.properties
export DOCKER_VERSION=$product_version
export DOCKER_REGISTRY=$docker_registry
export DOCKER_PREFIX=$docker_prefix

export HOSTNAME=$CURR_IP
docker-compose -f ./docker-compose.yml \
    -p slideshow \
    up -d
