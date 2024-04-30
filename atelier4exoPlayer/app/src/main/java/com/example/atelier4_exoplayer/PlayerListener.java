package com.example.atelier4_exoplayer;

import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

//
//public class PlayerListener implements Player.Listener {
//    @Override
//    public void onMediaItemTransition(@Nullable MediaItem mediaItem, int reason) {
//        Player.Listener.super.onMediaItemTransition(mediaItem, reason);
//    }
//
//    @Override
//    public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
//        if (reason == player.DISCONTINUITY_REASON_AUTO_TRANSITION && newPosition.periodIndex == player.getCurrentTimeline().getPeriodCount() - 1) {
//            // SI rendu a la fin du playlist
//            player.seekTo(0,0);
//        }
//    }
//}