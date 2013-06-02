/*
 * jdNewsScroll 1.1 (2007-02-08)
 *
 * Copyright (c) 2006,2007 Jonathan Sharp (http://jdsharp.us)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://jdsharp.us/
 *
 * Built upon jQuery 1.1.1 (http://jquery.com)
 * This also requires the jQuery dimensions plugin
 */
$(function() {
	$('div.jd_news_scroll').jdNewsScroll();
});
(function($){
 	var ELMS = [];
 	$.fn.jdNewsScroll = function(settings) {
		settings = $.extend({}, arguments.callee.defaults, settings);
		$(this).each(function(){
			this.$settings	= settings;
			this.$pause 	= false;
			// Randomize when the scrolling will start, afterwards it will pause by delay
			this.$counter	= (Math.floor(Math.random() * 10) * 10);
			$(this).hover(function(){ $(this).jdNewsScrollPause(true) }, function(){ $(this).jdNewsScrollPause(false) });
			$('> ul', this)
				.bind('mouseover', function(e) {
					if ($(e.target).is('li')) {
						$(e.target).addClass('hover');
					}
				})
				.bind('mouseout', function(e) {
					if ($(e.target).is('li')) {
						$(e.target).removeClass('hover');
					}
				});
			ELMS.push(this);
		});
		return this;
	};
	$.fn.jdNewsScroll.defaults = {
		// Delay in seconds is (delay * 85)/1000
		delay: 	60,
		// Number of pixels to step
		step:	2
	};
	$.fn.jdNewsScrollPause = function(pause) {
		return this.each(function() {
			this.$pause = pause;
		});
	}
	// Activate our 'scrolling agent'
	setInterval(scroll, 85);
	// Go through our list of elements and step each one
	function scroll() {
		for (var i = 0; i < ELMS.length; i++) {
			var elm = ELMS[i];
			if (elm && !elm.$pause) {
				if (elm.$counter == 0) {
					var ul 	= $('> ul', elm)[0];
					if (!elm.$steps) {
						// Set the number of steps (the height of the li element)
						elm.$steps 	= $('> li:last-child', ul).outerHeight();
						// Reset our step which will count backwards towards $steps
						elm.$step	= 0;
					}
					if ((elm.$steps + elm.$step) <= 0) {
						elm.$counter 	= elm.$settings.delay;
						elm.$steps 		= false;
						$(ul).css('top', '0').find('> li:last-child').after($('> li:first-child', ul));
						$('> *', ul).not('li').remove();
					} else {
						elm.$step -= elm.$settings.step;
						if (-elm.$step > elm.$steps) {
							elm.$step = -elm.$steps;
						}
						ul.style.top = elm.$step + 'px';
					}
				} else {
					elm.$counter--;
				}
			}
		}
	};
})(jQuery);
