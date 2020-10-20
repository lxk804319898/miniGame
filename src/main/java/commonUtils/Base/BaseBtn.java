package commonUtils.Base;

import commonUtils.consts.BtnParaConsts;

import javax.swing.*;

public class BaseBtn extends JButton {
    public BaseBtn() {
        this.setPreferredSize(BtnParaConsts.BTN_PREFERRED_SIZE);
    }

    public BaseBtn(Icon icon) {
        super(icon);
        this.setPreferredSize(BtnParaConsts.BTN_PREFERRED_SIZE);
    }

    public BaseBtn(String text) {
        super(text);
        this.setPreferredSize(BtnParaConsts.BTN_PREFERRED_SIZE);
    }

    public BaseBtn(Action a) {
        super(a);
        this.setPreferredSize(BtnParaConsts.BTN_PREFERRED_SIZE);
    }

    public BaseBtn(String text, Icon icon) {
        super(text, icon);
        this.setPreferredSize(BtnParaConsts.BTN_PREFERRED_SIZE);
    }
}
