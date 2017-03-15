#!/bin/bash

# Author:	Juan Sebastian Alvarez Eraso
# Date:		15/03/2017
# Version:	1.0

# Stop the Spring Boot container 
function stop_spring_boot {
	
	docker stop spring-boot
	}

# Start the Spring Boot container 
function start_spring_boot {
	
	docker start spring-boot
	}

# Removes the Spring Boot container 
function undeploy_spring_boot {
	
	docker stop spring-boot
	docker rm spring-boot
	}

# Starts the image for Java 8 and Spring Boot application
function deploy_spring_boot {

	pwd=$(pwd)

	# 1) Build the Spring Boot maven project
	cd ../SpringBoot/

	mvn clean install -Dmaven.test.skip=true
	mvn liquibase:update
	cp target/*jar $pwd/applications/spring-boot/

	cd $pwd

	# 2) Start the container
	cd applications/spring-boot/
	docker build -t spring-boot .
	#docker run -it --link mariadb:mariadb -d --name spring-boot spring-boot
	docker run --link mariadb --name spring-boot -p 19090:9090 spring-boot

	cd $pwd
	}

function spring_boot {
	
	opc=$1
	shift
	
	case $opc in
		
		deploy)
			undeploy_spring_boot
			deploy_spring_boot
			;;
		
		undeploy)
			undeploy_spring_boot
			;;
			
		start)
			start_spring_boot
			;;
			
		stop)
			stop_spring_boot
			;;
			
		*)
			echo 'Invalid option, use: deploy help'
			;;
		
	esac
	}

spring_boot $@