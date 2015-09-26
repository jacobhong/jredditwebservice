//Grunt is just JavaScript running in node, after all...
module.exports = function(grunt) {

  // All upfront config goes in a massive nested object.
  grunt.initConfig({
    // You can set arbitrary key-value pairs.
    srcJavascript: 'dependency_temp/js',
	concatJavascript: 'temp/concat/js',
	targetJavascript: 'src/main/webapp/assets/js',
	
	srcCss: 'dependency_temp/css',
	concatCss: 'temp/concat/css',
	targetCss: 'src/main/webapp/assets/css',
	
    // You can also set the value of a key as parsed JSON.
    // Allows us to reference properties we declared in package.json.
    pkg: grunt.file.readJSON('package.json'),
    // Grunt tasks are associated with specific properties.
    // these names generally match their npm package name.
    concat: {
      // Specify some options, usually specific to each plugin.
       options:{
        separator: ';'
      },
      js: {
        src: ['<%= srcJavascript %>/angular/angular.js',
			  '<%= srcJavascript %>/jquery/jquery.js',
			  '<%= srcJavascript %>/**/*.js'],
        dest: '<%= concatJavascript %>/jredditwebservice-js.js'
      },
      css:{
        src: ['<%= srcCss %>/**/*.css'],
        dest: '<%= concatCss %>/jredditwebservice-css.css'   
      }    
    },	
	uglify: {
		options: {
				banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n',
				compress: true,
				mangle: false
			},
		my_target: {
			files: {
				'<%= targetJavascript %>/jredditwebservice-min-js.js':['<%= concatJavascript %>/jredditwebservice-js.js']
			}				
		}		
	},
	cssmin: {
		css: {
			src:'<%= concatCss %>/jredditwebservice-css.css', 
			dest: '<%= targetCss %>/jredditwebservice-min-css.css'
		}
	  }
  }); // The end of grunt.initConfig

  // We've set up each task's configuration.
  // Now actually load the tasks.
  // This will do a lookup similar to node's require() function.
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-cssmin');


  // Register our own custom task alias.
  grunt.registerTask('default', ['concat', 'uglify', 'cssmin']);  
};