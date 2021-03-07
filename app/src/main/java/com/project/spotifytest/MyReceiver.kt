package com.project.spotifytest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.PlayerApi
import com.spotify.android.appremote.api.SpotifyAppRemote

class MyReceiver:BroadcastReceiver() {
    private val CLIENT_ID = "2b4672b5186a4e8a9ed5c59e521db52c"
    private val REDIRECT_URI = "http://com.project.spotifytest/callback"
    private lateinit var playerApi: PlayerApi
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("tut_receive", "YES")
        setupConnectWithSpotify(context)
        //Log.d("tut_receive", playerApi.toString())
        //playerApi.play("spotify:artist:2cFrymmkijnjDg9SS92EPM")
    }

    private fun setupConnectWithSpotify(context: Context?){
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()
        SpotifyAppRemote.connect(context, connectionParams, object: Connector.ConnectionListener {
            override fun onConnected(p0: SpotifyAppRemote?) {
                playerApi = p0?.playerApi!!

                p0?.playerApi?.play("spotify:artist:2cFrymmkijnjDg9SS92EPM")

                p0?.playerApi?.playerState?.setResultCallback { playerState->
                    Log.d("tut_track",playerState.track.toString())
                    Log.d("tut_track",playerState.track.name + playerState.track.album) }
            }

            override fun onFailure(p0: Throwable?) {
                Log.d("tut_onFailere", p0?.message,p0)
            }
        })
    }
}