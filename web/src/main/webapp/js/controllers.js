/* Controllers */

angular.module('registration.controllers', ['ui.bootstrap'])
	.controller('ModalWindowCtrl', [ '$scope', '$modal', '$log', '$anchorScroll',  function ($scope, $modal, $log, $anchorScroll) {
  		$scope.open = function () {
  	    $scope.modalInstance = $modal.open({
	      backdrop: 'static', // do not want it to be closed by clicking outside of modal window - instead user has X icon to close window
  	      keyboard: false, // do not want it to be closed by ESC key
  	      templateUrl: 'templateContent',
  	      controller: 'ModalInstanceCtrl',
  	      windowClass: 'dialogWindowClass',
  	      resolve: {
  	    	  appSecId: function() {
				  	if(!angular.isUndefined($scope.application)) {
				  		return $scope.application.id;
				}
			  },
              authorizations: function() { return function() {
            	  if(!angular.isUndefined($scope.currentApplication)) {
                    if($scope.currentApplication.authorizationBuilder) {
                        return $scope.currentApplication.authorizationBuilder;
                    }
            	  }
              	};
              },
              isRegistrationForm: function() {
                  if ($scope.appForm) {
                      return true;
                  }
                  else {
                      return false;
                  }
              }
  	      }
  	    });
  	  };
  	}]) 

    //TODO: The authorizations stuff needs to be broken out in to its own controller, really. -DCG
  	.controller('ModalInstanceCtrl', [ '$scope', '$rootScope', '$http', '$modalInstance', '$timeout', 'appSecId', 'authorizations', 'isRegistrationForm', 'Capco', '$anchorScroll', function ($scope, $rootScope, $http, $modalInstance, $timeout, appSecId, authorizations, isRegistrationForm, Capco, $anchorScroll) {
  	  // scroll to the top
  	  $anchorScroll();

      $scope.appSecId = appSecId;
      
    

      $scope.close = function() {
    	  $modalInstance.close();
  	  };
  	  
        //Authorization code
        $scope.allCapcoData = [];
        $scope.classifications =[];
        $scope.controlSystems = [];
        $scope.disseminationControls = [];
        $scope.nonICMarkings = [];
        $scope.citizenshipCountries = [];
        $scope.fveyCountryData = [];

        $scope.usaCitizenship = false;
        $scope.fveyCitizenship = false;
        
        $scope.initAuths = function() {
      	  	$scope.selectedAuthorizations = {	
						"classifications" : [],
						"controlSystems" : [],
						"disseminationControls" :[],
						"nonICMarkings" : [],
						"citizenshipCountries" : [],
						"fveyCountries" : [],
                        "organizations" : [],
						"externalGroups" : []
					};
        };
        
        $scope.savePortionMarkings = function() {
        	var temp = [];
            if($scope.selectedAuthorizations.classifications.length === 0) {
            	$scope.selectedAuthorizations.classifications.push("U");
            } else {
            	angular.copy($scope.selectedAuthorizations.classifications, temp);
            	$scope.selectedAuthorizations.classifications = [];
            	temp.forEach(function (classification){
                	$scope.selectedAuthorizations.classifications.push(classification.portion);
                });
            }

            angular.copy($scope.selectedAuthorizations.controlSystems, temp);
        	$scope.selectedAuthorizations.controlSystems = [];
        	temp.forEach(function (obj) {
                if (obj.portion) {$scope.selectedAuthorizations.controlSystems.push(obj.portion);}
            });

            angular.copy($scope.selectedAuthorizations.disseminationControls, temp);
            $scope.selectedAuthorizations.disseminationControls = [];
        	temp.forEach(function (obj) {
                if (obj.portion) {$scope.selectedAuthorizations.disseminationControls.push(obj.portion);}
            });

            angular.copy($scope.selectedAuthorizations.nonICMarkings, temp);
            $scope.selectedAuthorizations.nonICMarkings = [];
        	temp.forEach(function (obj) {
                if (obj.portion) {$scope.selectedAuthorizations.nonICMarkings.push(obj.portion);}
            });
            
            angular.copy($scope.selectedAuthorizations.citizenshipCountries, temp);
            $scope.selectedAuthorizations.citizenshipCountries = [];
        	temp.forEach(function(country){
        		$scope.selectedAuthorizations.citizenshipCountries.push(country.countryTrigraph);
        	});
            
            if ($scope.usaCitizenship){
            	$scope.selectedAuthorizations.citizenshipCountries.push("USA");
        	};
        	
        	$scope.selectedAuthorizations.fveyCountries = [];
            if ($scope.fveyCitizenship) {
            	$scope.fveyCountryData.forEach(function(country){
            		$scope.selectedAuthorizations.fveyCountries.push(country.countryTrigraph);
            	});
    		};
            
    		angular.copy($scope.selectedAuthorizations.externalGroups, temp);
    		$scope.selectedAuthorizations.externalGroups = [];
    		temp.forEach(function(group) {
                	$scope.selectedAuthorizations.externalGroups.push(group.type + group.name);
            });

            $rootScope.$emit('updatedPortionMarkings', $scope.selectedAuthorizations);
        };

        $scope.addExternalGroup = function(group) {
        	$scope.selectedAuthorizations.externalGroups.push(group);
        };

        $scope.removeExternalGroup = function(index) {
        	$scope.selectedAuthorizations.externalGroups.splice(index, 1);
        };
        
        $scope.setSelectedItems = function(capcoData, authorizations, key) {
        	if(angular.isUndefined(capcoData) || angular.isUndefined(authorizations) || angular.isUndefined(key)) {
        		return [];
        	};
        	
        	var temp = [];
        	capcoData.forEach(function(capco) {
        		authorizations.forEach(function(auth) {
        			if(capco[key] === auth) {
        				temp.push(capco);
        				if(auth === "USA") {
        					$scope.setUSACitizenship(true);
        				};
        			};
        		});
        	});
        	
        	return temp;
        };
        
  	  $scope.setCheckboxItems = function(capcoClassifications, authorizations) {
		  if(!angular.isUndefined(authorizations.fveyCountries) && authorizations.fveyCountries.length === 5) {
			  $scope.setUSACitizenship(true);
			  $scope.setFveyCountries(true);
		  }
		  
		  if(!angular.isUndefined(capcoClassifications) && authorizations.classifications) {
			  var temp = [];
			  capcoClassifications.forEach(function(classification){
				 authorizations.classifications.forEach(function(auth){
					 if(classification.portion === auth) {
						 temp.push(classification);
					 }
				 }) ;
			  });
			  if(temp.length > 0) {
				  temp.sort($scope.sortByRanking);
				  temp[0].selected = temp[0].portion;
				  $scope.setClassifications(temp[0]);
			  };
		  }; 
		  
  	  	};
  	  	
    	  $scope.setExternalGroups = function(groups) {
    		  var temp = [];
    		  if(!angular.isUndefined(groups)) {
    			  groups.forEach(function(group){
    	        		var split = group.split("_");
    	          		if(split.length === 2 ) {
    	              		group = {"type" : split[0] + "_", "name" : split[1]};
    	              		temp.push(group);
    	          		};
    			  });
    		  };
    		  
    		  return temp;
      	  };
        
        $scope.populateAuthorizationBuilder = function(markings) {
            Capco.get().$promise.then(function(allCapco) {
              $scope.allCapcoData = allCapco;
              $scope.getFvey();
              
              var capcoData = {"classifications": allCapco.classifications[0], "countryData": allCapco.countryData};

              //Populate all class data
              capcoData.classifications.groups.forEach(function (obj) {
                  switch (obj.markingGroup.toUpperCase()) {
                      case "US_CLASSIFICATION":
                          obj.markings.forEach(function (marking) {
                        	  $scope.classifications.push({"title": marking.title, "portion": marking.portion, "classifRank": marking.classifRank, "selected": false});  
                          });
                          break;
                      case "SCI_CONTROLS":
                          obj.markings.forEach(function (marking) {
                              $scope.controlSystems.push({"title": marking.title, "portion": marking.portion});
                          });
                          break;
                      case "DISSEM_CONTROLS":
                          obj.markings.forEach(function (marking) {
                              $scope.disseminationControls.push({"title": marking.title, "portion": marking.portion});
                          });
                          break;
                      case "NON_IC_CONTROLS":
                          obj.markings.forEach(function (marking) {
                              $scope.nonICMarkings.push({"title": marking.title, "portion": marking.portion});
                          });
                          break;
                      default:
                          console.log("Unknown classifcation data: '" + obj.markingGroup + "'. Skipping.");
                          break;
                  }
              });

              //Populate all country data
              capcoData.countryData.forEach(function (obj) {
                  $scope.citizenshipCountries.push({"countryName": obj.countryName, "countryTrigraph": obj.countryTrigraph});
              });
              
              // if there are any previously selected markings then populate window
              $scope.currentClassification = [];
              if(!angular.isUndefined($scope.selectedAuthorizations)) {
            	  $scope.setCheckboxItems($scope.classifications, $scope.selectedAuthorizations);
            	  $scope.selectedAuthorizations.controlSystems = $scope.setSelectedItems($scope.controlSystems, $scope.selectedAuthorizations.controlSystems, "portion");
            	  $scope.selectedAuthorizations.disseminationControls = $scope.setSelectedItems($scope.disseminationControls, $scope.selectedAuthorizations.disseminationControls, "portion");
            	  $scope.selectedAuthorizations.nonICMarkings = $scope.setSelectedItems($scope.nonICMarkings, $scope.selectedAuthorizations.nonICMarkings, "portion");
            	  
            	  $scope.selectedAuthorizations.citizenshipCountries = $scope.setSelectedItems($scope.citizenshipCountries, $scope.selectedAuthorizations.citizenshipCountries, "countryTrigraph");
            	  $scope.selectedAuthorizations.fveyCountries =  $scope.setSelectedItems($scope.citizenshipCountries, $scope.selectedAuthorizations.fveyCountries, "countryTrigraph");
            	  $scope.selectedAuthorizations.fveyCountries.forEach(function(country){
            		  $scope.selectedAuthorizations.citizenshipCountries.push(country);
            	  });
            	  
            	  $scope.selectedAuthorizations.externalGroups = $scope.setExternalGroups($scope.selectedAuthorizations.externalGroups);
              } else {
            	  $scope.initAuths();
              };
            });
        };
        
        $scope.getFvey = function() {
            var temp = $scope.allCapcoData.orgData.filter(function (org) {
                var val = org.orgTetragraph.toLowerCase() == "fvey";
                return val;
            })[0];
            
            temp.orgTrigraphs.forEach(function (fveyCountry) {
            	$scope.fveyCountryData.push($scope.allCapcoData.countryData.filter(function (country) {
                    var val = country.countryTrigraph == fveyCountry;
                    return val;
                })[0]);
            });
        };
        
        $scope.sortByRanking = function(objA, objB) {
        	var A = objA.classifRank;
        	var B = objB.classifRank;
        	return ((A < B) ? 1 : 0);
        };

        
        $scope.setFveyCountries = function(selected) {
        	$scope.fveyCitizenship = selected;
        };
        
        $scope.setUSACitizenship = function(selected) {
        	$scope.usaCitizenship = selected;
        };

        //Adds lower ranked classes of the selected class to the list.
        $scope.setClassifications = function(selectedClass) {
        	$scope.selectedAuthorizations.classifications = [];
            this.classifications.forEach(function (classification) {
                if (selectedClass.classifRank >= classification.classifRank) {
                	$scope.selectedAuthorizations.classifications.push(classification);
                }
            });
        };

        var auths = authorizations();

        if (isRegistrationForm) {
            if (!angular.isUndefined(auths)) {
            	$scope.selectedAuthorizations = auths;
                $scope.populateAuthorizationBuilder(auths);
            }
            else {
                $scope.populateAuthorizationBuilder();
            }
        }
  	}])

    .controller('FileUploadCtrl', ['$scope', '$http', '$location', 'AppImport', function($scope, $http, $location, AppImport) {
        $scope.showImportAppBtn = true;

        $scope.importApplicationReg = function($files) {
            $scope.showImportAppBtn = false;
            var fileReader = new FileReader();
            fileReader.onload = function(event) {
                try {
                    var app = JSON.parse(fileReader.result);
                    AppImport.set(app);
                }
                catch(e) {
                    console.log("Unexpected error: " + e);
                }
            };
            fileReader.readAsText($files[0]); //TODO: Might want to support multiple file upload as the module already does. -DCG
            $location.path('/application/0');
        };
        
        
    	$scope.appIconSrc = "images/appIcon.png";	
    	$scope.showDefaultAppIcon = (angular.isUndefined($scope.currentApplication) || angular.isUndefined($scope.currentApplication.appIconSrc)) ? true : false;
        	
        $scope.uploadAppIcon = function($files) {
            if ($files[0].type.match('image.*')) {
                var fileReader = new FileReader();
                fileReader.readAsDataURL($files[0]);
                fileReader.onload = function(event) {
                	$scope.currentApplication.appIconSrc = fileReader.result;
                	$scope.showDefaultAppIcon = false;
                    $scope.$apply();
                };
            }
        };
        
        $scope.uploadThrift = function($files, topic, topicDetails) {
            var fileReader = new FileReader();
            fileReader.onload = function(event) {
                try {
                    topic.thriftDefinition = fileReader.result;
                    topicDetails.thriftFileName = $files[0].name;
                    $scope.$apply();
                }
                catch(e) {
                    console.log("Unexpected error: " + e);
                }
            };

            fileReader.readAsText($files[0]);
        };
}])

  .controller('FeedsCtrl', ['$scope', 'Feed', function($scope, Feed) {
    $scope.feeds = Feed.query();
}])

  .controller('AppsCtrl', ['$scope', '$rootScope', 'Application', '$http', '$location', '$timeout', '$route', 'toaster', function($scope, $rootScope, Application, $http, $location, $timeout, $route, toaster) {
    $scope.homeEnabled = 0;
  	$scope.myApplications = Application.query();
  	$scope.simple = true;

    $scope.myApplications.$promise.then(function (data) {
        if ($route.current.action){
            $scope.homeEnabled = parseInt($route.current.action);
        }
        else {
            $scope.homeEnabled = (data.length === 0) ? 1 : 2; //We're using Numbers here to ensure that the toggle is falsey for both elements in the view prior to the promise being fulfilled.
                                                              //Once the promise is fulfilled, then the two elements will toggle.
        }
    });

    $scope.linkTo = function(path) {
        $location.path(path);
    };
  	
    $scope.unDeploy = function(application, $index, $event) {
        $scope.showUndeployLoader++; //While showUndeployLoader is not zero, it will be true in ng-show.
    	var pkg = application.packages[$index]; 
    	
    	$http.post('rest/application/undeploy/' + pkg.appName +"/" + pkg.appServiceId, null )
            .success(function(response) {
    		    application.packages.splice($index, 1);
                $scope.showUndeployLoader--;
            })
            .error(function(response){
                $scope.showUndeployLoader--;
                toaster.pop('error', "Deployer Error", "Failed to undeploy from ezDeployer.  Please check the logs");
            });
    };

    $scope.unStage = function(application, $index) {
      var pkg = application.packages[$index];

      $http.post('rest/application/unstage/' + pkg.appName +"/" + pkg.appServiceId, null )
          .success(function(response) {
            application.packages.splice($index, 1);
          })
          .error(function() {
              toaster.pop('error', "Deployer Error", "Failed to unstage from ezDeployer.  Please check the logs");
          });
    };
    
    $scope.getListOfDeployedPackages = function(application, $event) {
    	if( application.show == null || application.show == false ) {
            $http.get('rest/application/deployedServices/' + application.id)
                .success(function(result) {
            	    application.packages = result;
                })
                .error(function() {
                    application.packages = [{"appName":"","appSecId":"", "appServiceId":"FAILED TO GET DEPLOYER STATUS", "status": "Please Try Again"}];
                    toaster.pop('error', "Deployer Error", "Failed to get information from ezDeployer.  Please check the logs");
                });
    	} else {
    		application.packages = null;
    	};
    	
    	application.show = !application.show;
    }; 
    
    $scope.deleteApplication = function(id) {
      Application.delete({id: id},null,function() {$scope.myApplications = Application.query()});
    };

    $scope.downloadApplication = function(id) {
        window.open('rest/application/download/' + id, '_blank');
    }
    
	$scope.registrationStatus = function(application) {
        $http.get('rest/application/registrationStatus/' + application.id)
            .success(function(result) {
                application.registrationStatus = result;
                if(application.registrationStatus === "ACTIVE") {
                    application.isDeployShow = true;
                } else {
                    application.isDeployShow = false;
                }
            })
            .error(function() {
                application.registrationStatus = "FAILED TO RETRIEVE APPLICATION STATUS";
                toaster.pop('error', "INS Error", "Failed to get information from INS.  Please check the logs");
            });
    };

	$scope.isAllowedToRegisterApplication = function() {
		$http.get('rest/application/allowedToRegister')
            .success(function(result) {
			    $scope.isAllowedToRegister = result;
		    })
            .error(function(data, status) {
                if (status >= 500) {
                    toaster.pop('error', "Registration Error", "Failed to get information from EzSecurityRegistration.  Please check the logs");
                }
            });
    };
	
    $scope.files = {};
	$scope.formatFileAttributes = function($files, isAppManifest, isAppProperties, isTarGz) {
		if(isAppProperties) {
			$scope.appProperties = [];
			var sz = $files.length;
			if(sz > 0) {
				$scope.files["appPropertiesArray"] = $files;
			};
			for(var i=0; i < $files.length; i++) {
				$scope.appProperties.push($files[i].name);
			};
		};
        if (isTarGz) {
            $scope.appTar = $files[0].name;
  			$scope.files["appTar"] = $files[0];
        };
	};
	
	$scope.validateArchiveFile = function($files) {
		var fileReader = new FileReader();
		fileReader.readAsArrayBuffer($files[0].slice(0, 4));
		
        fileReader.onload = function(event) {
	        try {
	        	
	            $http.post('rest/application/validateArchiveFile/' + new DataView(fileReader.result).getUint32(0, false))
	            	.success(function(result) {
	            		$scope.appArchive = $files[0].name;
	            		$scope.files["appArchive"] = $files[0];
	            	}).error(function(result){
	            		$scope.appArchive = ""; // in case it is populated, reset it, so that form validation works properly
	            	});
	        }
	        catch(e) {
	        	console.log(e);
	        	$scope.appArchive = "";
	        };
	    };
	};
	
	$scope.manifestValidationMessage = "Valid application manifest file required.";
	$scope.validateManifestFile = function(appSecId, $files) {
		var fileReader = new FileReader();
		fileReader.readAsDataURL($files[0]);
		
		fileReader.onload = function(event) {
			try {
				var base64 = fileReader.result.split(",")[1];
					$http.post('rest/application/validateManifestFile/' + appSecId + '/' + base64 ).success(function(data) {
						if(data.length > 0) {
							$scope.manifestValidationMessage = "ERROR:" + data.split(":")[0];
						} else {
							$scope.appManifest = $files[0].name;
							$scope.files["appManifest"] = $files[0]; 
						}
					}).error(function(error) {
						$scope.appManifest = ""; // in case it is populated, reset it, so that form validation works properly
					});
			} catch(e) {
				console.log(e);
				$scope.appManifest = "";
			};
		};
	};
	
	  $scope.showSpinner = false;
	  $scope.deployerSpinner = function() {
		  $scope.showSpinner = !$scope.showSpinner;
	  };
	  
	  $scope.isValidForm = function() {
		  return ( ($scope.simple && $scope.deployForm.archiveFile.$valid && $scope.deployForm.manifestFile) || 
				  (!$scope.simple && $scope.deployForm.tarArchiveFile.$valid && $scope.deployForm.manifestFile.$valid) ); // if either combination is true, then form is valid
	  };
	  
      var endPollCleanUp = function(appSecId, pollingData) {
          // remove task from the poller
          $http.post("rest/application/removeFromDeploymentStatusMap/" + appSecId).success(function(data, status){
              if(status === 200 ) {
            	  $scope.uploadingMessage += "Done";
              } 
          });

          if (pollingData) {
              // if there were errors get list of service ids that failed to deploy
              var someErrors = false; // for tasks that do not have service ids
              var erroredOutServiceIds = [];
              for (var i = 0; i < pollingData[appSecId].length; i++) {
                  if (pollingData[appSecId][i].error === true) {
                      if (angular.isDefined(pollingData[appSecId][i].serviceId)) {
                          erroredOutServiceIds.push(pollingData[appSecId][i].serviceId);
                      }
                      someErrors = true;
                  };
              }

              if (erroredOutServiceIds.length > 0) {
                  var deployMessage = "Failed to Deploy Following Services For App Id:" + appSecId + ": " + erroredOutServiceIds.join(", ");
                  toaster.pop('error', "Deployer Error", deployMessage + " Please check the logs");
              } else if (someErrors === true) {
                  toaster.pop('error', "Deployer Error", "Failed to deploy entire or part of the application. Please check the logs");
              } else {
                  //everything is good
            	  toaster.pop('success', "Deployed", "App Id: " + appSecId + " successfully deployed.");
                  $scope.close();
              }
          };
      };

		$scope.pollDeploymentStatus = function(appSecId) {
			 var sleep = 10000;// sleep in  milliseconds
			 
			 (function tick() {
				 $http.get("rest/application/pollDeploymentStatusMap/" + appSecId).success(function(data, status) {
					 var isDeployTaskDone = false;
					
					 if(jQuery.isEmptyObject(data)) {  //	if result is empty, sleep and try again
						 $timeout(tick, sleep);
					 } else if(status === 400 ) { // bad request, stop polling for given appSecId
						 endPollCleanUp(appSecId, null);
					 } else {
						 var truths = 0;
						 var sz = data[appSecId].length;
						 for(var i = 0; i < sz; i++) {
							 if(data[appSecId][i].future.done === true) {
								 truths++;
							 };
						 }
						 isDeployTaskDone = truths === sz || false;
                       if(!isDeployTaskDone) {
                           $timeout(tick, sleep);
                       } else {
                           endPollCleanUp(appSecId, data);
                       };
					 };
				 }).error( function() {
					 toaster.pop('error', "Deployer Error", "Failed to deploy entire or part of the application. Please check the logs");
					 endPollCleanUp(appSecId, null);
				 });
			 })();
		 };
		 
		 $scope.prepForm = function(appSecId) {
			var formData = new FormData(document.getElementById("deployFormData"));
			
			$scope.files["appSecId"] = appSecId; // pass in app id
			angular.forEach($scope.files, function(v, k){
				if(k == "appPropertiesArray") {
					angular.forEach(v, function(val, key){
						formData.append("appProperties", val);
					});
				} else {
					formData.append(k, v);	
				}
			});
			
			$scope.sendForm(formData, appSecId);
		 };
		 
		 $scope.sendForm = function(formData, appSecId) {
			$scope.uploadingMessages = "";
			var xhr = new XMLHttpRequest();
				
			 // while uploading file
			 xhr.upload.onprogress = function(data) {
		            $scope.$apply(function() {
		                var sofar;
		                if (data.lengthComputable) {
		                    sofar = Math.round(data.loaded / data.total * 100);
		                    if (sofar < 100) {
		                        $scope.uploadingMessage = "...Uploading " + sofar + "%";
		                    } else if (sofar == 100) {
		                        $scope.uploadingMessage = "...Uploaded -> Saving Data";
		                    };
		                };
		            });
		        };

		        // finished uploading, ready for server processing
		        xhr.upload.onload = function(data) {
		        	$scope.$apply(function() {
		        		$scope.uploadingMessage += " -> Server Processing...";
		        	});
		        };
		        
		        // uploaded, server processing, poll for status
		        xhr.upload.onloadend = function(data) {
		        	$scope.pollDeploymentStatus(appSecId); // if there was an error, or abort, this call will follow onerror/onabort function		        	
		        }

		        // handle errors
		        xhr.upload.onerror = function(error) {
		        	toaster.pop('error', "Deployer Error", "Failed to deploy entire or part of the application. Please check the logs");
		        	$scope.deployerSpinner();
		        }
		        
		        xhr.open('POST', 'rest/application/deploy');
		        xhr.send(formData);
		 };
}]);
