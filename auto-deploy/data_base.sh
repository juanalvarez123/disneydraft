#!/bin/bash

# Author:	Juan Sebastian Alvarez Eraso
# Date:		15/03/2017
# Version:	1.0

# Stop the mariadb container 
function stop_data_base {
	
	docker stop mariadb
	}

# Start the mariadb container 
function start_data_base {
	
	docker start mariadb
	}

# Removes the mariadb container 
function undeploy_data_base {
	
	docker stop mariadb
	docker rm mariadb
	}

# Pull from docker images a mariadb image and start it on a container
function deploy_data_base {
	
	docker pull mariadb
	docker run --name mariadb -e MYSQL_ROOT_PASSWORD=12345 -p 13306:3306 -d mariadb:latest	
	}

function data_base {
	
	opc=$1
	shift
	
	case $opc in
		
		deploy)
			undeploy_data_base
			deploy_data_base
			;;
		
		undeploy)
			undeploy_data_base
			;;
			
		start)
			start_data_base
			;;
			
		stop)
			stop_data_base
			;;
			
		*)
			echo 'Invalid option, use: deploy help'
			;;
		
	esac
	}

data_base $@