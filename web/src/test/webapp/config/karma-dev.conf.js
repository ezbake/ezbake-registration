module.exports = function(config){
  config.set({
	basePath : '../../../',
    files : [
             'main/webapp/lib/jquery-1.10.2.js',
             'main/webapp/lib/angular-file-upload/angular-file-upload-shim.min.js',
             'main/webapp/lib/angular/angular.min.js',
             'main/webapp/lib/angular/angular-route.min.js',
             'main/webapp/lib/angular/angular-resource.min.js',
             'main/webapp/lib/angular/angular-cookies.min.js',
             'main/webapp/lib/angular/angular-mocks.min.js',
             'main/webapp/lib/angular/angular-animate.min.js',
             'main/webapp/lib/angular-file-upload/angular-file-upload.min.js',             
             'main/webapp/lib/ui-bootstrap-tpls-0.10.0.min.js',
             'main/webapp/js/*.js',
             'test/webapp/unit/*.js'
    ],

    exclude : [
      'lib/angular/angular-loader.js',
      'lib/angular/*.min.js',
      'lib/angular/angular-scenario.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
      'karma-junit-reporter',
      'karma-chrome-launcher',
      'karma-script-launcher',
      'karma-jasmine'
    ],

    junitReporter : {
      outputFile: 'dev-test-results.xml',
/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

      suite: 'unit'
    }
  });
};
