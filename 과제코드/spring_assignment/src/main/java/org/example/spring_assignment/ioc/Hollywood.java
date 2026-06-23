package org.example.spring_assignment.ioc;

import java.util.ArrayList;
import java.util.List;

interface ClickListener {
    void onClick();
}

class Button {
    private ClickListener listener;
    private List<ClickListener> listenerList = new ArrayList<>();
    public void setListener(ClickListener listener) {
        this.listener = listener;
    }
    public void addListener(ClickListener listener) {
        listenerList.add(listener);
    }
    public void press() {
        System.out.println("[시스템] 버튼이 눌렸습니다");
        listener.onClick();
    }
    public void pressAll() {
        System.out.println("[시스템] all 버튼이 눌렸습니다");
        for (ClickListener listener : listenerList) {
            listener.onClick();
        }
    }
}

class LikeAction implements ClickListener {
    @Override
    public void onClick() {
        System.out.println("내 코드 실행: 좋아요!");
    }
}


