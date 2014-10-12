module.exports = function(config){
  config.set({
    basePath : '../../../main/webapp/',

    files : [
      'lib/angular/angular.js',
      'lib/angular/angular-*.js',
      'js/*.js',
      '../../test/webapp/unit/*.js'
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
      'karma-firefox-launcher',
      'karma-script-launcher',
      'karma-jasmine'
    ],

    junitReporter : {
      outputFile: 'dev-test-results.xml',
      suite: 'unit'
    }
  });
};