package com.qqw.supermarket.filter;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

import javax.servlet.annotation.WebFilter;

/**
 * @Classname ApplicationSIteMeshFilter
 * @Description TODO()
 * @Date 2020/2/15 9:58
 * @Author by Alex
 */
@WebFilter(value = "/*", filterName = "sitemeshFilter")
public class ApplicationSIteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        //初始化装饰器
        //把所有响应页面都用 application.jsp装饰
        builder.addDecoratorPath("/*", "/WEB-INF/pages/management/admin/sitemesh/application.jsp")
                //满足该匹配路径将不被装饰
                .addExcludedPath("/login")
                .addExcludedPath("/*/iframe/**")
                //添加自定义标签
                .addTagRuleBundle(new CssTagRule())
                .addTagRuleBundle(new JsTagRule());
    }
}

class CssTagRule implements TagRuleBundle {

    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("css",new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("css"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

    }
}

class JsTagRule implements TagRuleBundle{
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("js",new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("js"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

    }
}