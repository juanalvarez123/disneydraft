#!/bin/bash

# Author:	Juan Sebastian Alvarez Eraso
# Date:		15/03/2017
# Version:	1.0

function deploy {
	
	opc=$1
	shift
	
	case $opc in
		
		deploy)
			echo 'Build and start everything'
			sh data_base.sh deploy
			# Need those 10 seconds to wait while mariadb container start completely
			sleep 10s
			sh spring_boot.sh deploy
			;;
			
		undeploy)
			echo 'Destroy everything'
			sh data_base.sh undeploy
			sh spring_boot.sh undeploy
			;;
		
		start)
			echo 'Start services'
			sh data_base.sh start
			sh spring_boot.sh start
			;;
		
		stop)
			echo 'Stoping services'
			sh data_base.sh stop
			sh spring_boot.sh stop
			;;
		
		help)
			cat README
			;;
		
		*)
			echo 'Invalid option, use: deploy help'
			;;
		
	esac
	}

deploy $@