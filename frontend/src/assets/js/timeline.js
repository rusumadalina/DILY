$(document).ready(function () {
    $('.icon-container i').click(function(){
        console.log('apasat');
        $(this).parents('app-left-menu').find('.left-inside').toggleClass("hide");
        $(this).parents('app-left-menu').find('.hided-inside').toggleClass("show");
        $(this).parents('app-left-menu').toggleClass("shrink");
        $(this).parents('.main-container').find('.right-container').toggleClass("expand");
        $(this).toggleClass("rotate");
    });

    $('.custom-notification').click(function(){
        $(this).find('.notification-dropdown').toggleClass("show");
    });
});




