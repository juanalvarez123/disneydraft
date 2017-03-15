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
			;;
			
		undeploy)
			echo 'Destroy everything'
			sh data_base.sh undeploy
			;;
		
		start)
			echo 'Start services'
			sh data_base.sh start
			;;
		
		stop)
			echo 'Stoping services'
			sh data_base.sh stop
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