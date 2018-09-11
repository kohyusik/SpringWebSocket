/**
 @FileName    : websocket
 @Description :
 @Author      : kohyusik
 @Since       : 2018-09-11
 @Version     : 1.0
 @개정이력     :
 수정일             수정자             수정내용
 ----------        -------------    ----------------------------
 2018-09-11           kohyusik          최초생성
 */

requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: 'public/js',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
        websocket: './websocket'
    }
});