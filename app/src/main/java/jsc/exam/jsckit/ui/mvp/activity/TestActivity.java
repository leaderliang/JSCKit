package jsc.exam.jsckit.ui.mvp.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jsc.exam.jsckit.BuildConfig;
import jsc.exam.jsckit.ui.mvp.model.TestModel;
import jsc.exam.jsckit.ui.mvp.presenter.TestPresenter;
import jsc.exam.jsckit.ui.mvp.view.CommonView;
import jsc.exam.jsckit.ui.mvp.view.ITestView;
import jsc.kit.component.baseui.ABaseMVPActivity;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/JSCKit" target="_blank">https://github.com/JustinRoom/JSCKit</a>
 *
 * @author jiangshicheng
 * @createTime 2018-06-06 1:45 PM Wednesday
 */
public class TestActivity extends ABaseMVPActivity implements ITestView, CommonView {

    private TextView textView;
    private TestPresenter testPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        setContentView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setTitle(getClass().getSimpleName().replace("Activity", ""));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TestActivity", "onClick: ");
            }
        });

        testPresenter = new TestPresenter(this, new TestModel());
        testPresenter.setCommonView(this);
        addToPresenterManager(testPresenter);
        sendUIEmptyMessageDelay(0, 350L);
    }

    @Override
    public void handleUIMessage(Message msg) {
        super.handleUIMessage(msg);
        testPresenter.loadVersionInfo();
    }

    @Override
    public boolean isShowNetLog() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String getBaseUrl() {
        return "https://raw.githubusercontent.com/";
    }

    @Override
    public String getToken() {
        return "54s5dfsd1fpasjfshlasdfhsan";
    }

    @Override
    public String getCurrentUserId() {
        return "20185612";
    }

    @Override
    public Dialog getLoadingDialog() {
        return createLoadingDialog();
    }

    @Override
    public void onLoadVersionInfo(String result) {
        if (!TextUtils.isEmpty(result)){
            textView.setText(result);
        } else {
            textView.setText("");
        }
    }
}
