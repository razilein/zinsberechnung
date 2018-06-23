module.exports = function(grunt) {
  'use strict';
  grunt.initConfig(
  {
    pkg: grunt.file.readJSON('package.json'),
    copy: {
      main: {
        files: [
          {src: 'node_modules/axios/dist/axios.min.js', dest: 'src/main/resources/static/scripts/vendor/axios.min.js'},
          {src: 'node_modules/vue/dist/vue.min.js', dest: 'src/main/resources/static/scripts/vendor/vue.min.js'},
        ],
      }
    },
    jshint: {
      all: ['Gruntfile.js', 'src/main/resources/static/scripts/*.js']
    },  
  });
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-jshint');

  grunt.registerTask('default', [
    'jshint'
  ]);
};