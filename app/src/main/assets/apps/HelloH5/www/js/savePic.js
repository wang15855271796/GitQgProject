/**
 * 1. 获取图片的链接
 * 2. 创建下载并下载图片
 * 3. 保存至相册
 */
function saveImage(target) {
    var imgUrl = target.src;
    var timestamp = (new Date()).valueOf();
    var downLoader = plus.downloader.createDownload(imgUrl, {
        method: 'GET',
        filename: '_downloads/image/' + timestamp + '.png'
    }, function(download, status) {
        var fileName = download.filename;
        /**
         * 保存至本地相册
         */
        plus.gallery.save(fileName, function() {
            mui.toast("保存成功");

        });
    });
    
    /**
     * 开始下载任务
     */
    try{
        downLoader.start();
    }catch(e){
        //TODO handle the exception
        mui.toast("请长按图片保存");
    }
}

 /**
 * 长按保存图片
 * 1. 获取图片链接
 * 2. 创建下载并下载图片
 * 3. 保存至相册
 */
function savePic(target) {
    if(target.tagName == 'IMG' && target.currentSrc.length > 0 ) { //确保图片链接不为空
        var imgUrl = target.src;

        var suffix = cutImageSuffix(imgUrl);

        mui.confirm("是否保存此图片", "", ["保存", "取消"], function(event) {
            var index = event.index;
            if(index == 0) {
                var timestamp = (new Date()).valueOf();
                var downLoader = plus.downloader.createDownload(imgUrl, {
                    method: 'GET',
                    filename: '_downloads/image/' + timestamp+'.png'
                }, function(download, status) {
                    var fileName = download.filename;
                    /**
                     * 保存至本地相册
                     */
                    plus.gallery.save(fileName, function() {
                        mui.toast("保存成功" );

                    });
                });
                /**
                 * 开始下载任务
                 */
                downLoader.start();
            }
        });
    }
}
// 截取图片后缀用于重命名图片，防止%E5%85%89%E6%98%8E%E8%A1%8C编码的文件不被系统相册识别；
function cutImageSuffix(imageUrl) {
    var index = imageUrl.lastIndexOf('.');
    return imageUrl.substring(index);
}