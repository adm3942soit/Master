function playPause() {

    var myVideo = document.getElementsByTagName('video')[0];

    if (myVideo.paused)

        myVideo.play();

    else

        myVideo.pause();

}

function makeBig() {

    var myVideo = document.getElementsByTagName('video')[0];

    myVideo.height = myVideo.videoHeight * 2;

}

function makeNormal() {

    var myVideo = document.getElementsByTagName('video')[0];

    myVideo.height = myVideo.videoHeight;

}
function getPercentProg() {

    var myVideo = document.getElementsByTagName('video')[0];

    var endBuf = myVideo.buffered.end(0);

    var soFar = parseInt(((endBuf / myVideo.duration) * 100));

    document.getElementById("loadStatus").innerHTML =  soFar + '%';

}

function myAutoPlay() {

    var myVideo = document.getElementsByTagName('video')[0];

    myVideo.play();

}

function addMyListeners() {

    var myVideo = document.getElementsByTagName('video')[0];

    myVideo.addEventListener('progress', getPercentProg, false);

    myVideo.addEventListener('canplaythrough', myAutoPlay, false);
}

