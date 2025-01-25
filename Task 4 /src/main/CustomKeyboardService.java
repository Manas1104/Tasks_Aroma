package com.example.customkeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;

public class CustomKeyboardService extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        keyboard = new Keyboard(this, R.xml.keyboard);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {}

    @Override
    public void onRelease(int primaryCode) {}

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        getCurrentInputConnection().commitText(String.valueOf((char) primaryCode), 1);
    }

    @Override
    public void onText(CharSequence text) {}

    @Override
    public void swipeLeft() {}

    @Override
    public void swipeRight() {}

    @Override
    public void swipeDown() {}

    @Override
    public void swipeUp() {}
}
