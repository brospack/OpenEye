package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.CategoryContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/17 10:27
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    override fun getAllCategory() {
        checkViewAttached()
        DataRepository.getInstance()
            .getCategories(object : BaseObserver<BaseBean>() {
                override fun onSuccess(data: BaseBean) {
                    rootView?.hideLoading()
                    rootView?.showContent(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }

}