package com.lisuperhong.openeye.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.VideoSmallCard
import com.lisuperhong.openeye.ui.activity.CategoryDetailActivity
import com.lisuperhong.openeye.ui.activity.SpecialTopicsActivity
import com.lisuperhong.openeye.ui.activity.TagDetailActivity
import com.lisuperhong.openeye.ui.activity.VideoDetailActivity

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/20 23:28
 * Github: https://github.com/lisuperhong
 * Desc:
 */
object JumpActivityUtil {

    fun startVideoDetail(activity: Activity, view: View, videoSmallCard: VideoSmallCard) {
        val intent = Intent(activity, VideoDetailActivity::class.java)
        intent.putExtra(Constant.INTENT_VIDEO_DETAIL, videoSmallCard)
        intent.putExtra(VideoDetailActivity.FROM_TRANSITION, true)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, VideoDetailActivity.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, pair
            )
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }

    fun parseActionUrl(context: Context, actionUrl: String) {
        val uri: Uri = Uri.parse(actionUrl)
        val host = uri.host
        val path = uri.pathSegments[0]
        when (host) {

            "categories" -> { // 全部分类

            }

            "category" -> { // 分类详情
                val intent = Intent(context, CategoryDetailActivity::class.java)
                intent.putExtra(Constant.INTENT_CATEGORY_ID, path.toLong())
                context.startActivity(intent)
            }


            "tag" -> { // 标签详情
                val intent = Intent(context, TagDetailActivity::class.java)
                intent.putExtra(Constant.INTENT_TAG_ID, path.toLong())
                context.startActivity(intent)
            }

            "campaign" -> { // 近期专题
                val intent = Intent(context, SpecialTopicsActivity::class.java)
                context.startActivity(intent)
            }

            "ranklist" -> { // 排行榜

            }

            "common" -> {

            }
            
            "webview" -> {

            }

        }

    }
}