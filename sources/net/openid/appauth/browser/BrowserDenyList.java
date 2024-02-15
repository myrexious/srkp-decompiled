package net.openid.appauth.browser;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class BrowserDenyList implements BrowserMatcher {
    private List<BrowserMatcher> mBrowserMatchers;

    public BrowserDenyList(BrowserMatcher... matchers) {
        this.mBrowserMatchers = Arrays.asList(matchers);
    }

    @Override // net.openid.appauth.browser.BrowserMatcher
    public boolean matches(BrowserDescriptor descriptor) {
        for (BrowserMatcher browserMatcher : this.mBrowserMatchers) {
            if (browserMatcher.matches(descriptor)) {
                return false;
            }
        }
        return true;
    }
}
