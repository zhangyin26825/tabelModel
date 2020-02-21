package com.maqv.model.code.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.mysql.JdbcUtil;
import com.maqv.model.code.mysql.MysqlColumnInfo;
import com.maqv.model.code.task.ControllerServiceTemplateTask;
import com.maqv.model.code.ui.listselect.DataListSelect;
import com.maqv.model.code.ui.listselect.ListValue;
import com.maqv.model.code.ui.listselect.SelectedCallback;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-16 17:00
 **/
public class ControllerServiceTemplateAction  extends CommonAction {

    @Override
    public void ownActionPerformed(AnActionEvent e) {
        List<MysqlColumnInfo> columns = JdbcUtil.queryColumns();
        List<Table> tables = MysqlColumnInfo.convert(columns);

        SelectedCallback selectedCallback=new SelectedCallback() {
            @Override
            public void handleSelectValue(ListValue listValue) {
                if(listValue instanceof  Table){
                    Table table = (Table) listValue;
                    ControllerServiceTemplateTask templateTask=new ControllerServiceTemplateTask(table);
                    templateTask.createControllerServiceTemplate();
                }
            }
        };
        List<ListValue> listValues=new ArrayList(tables);
        DataListSelect dataListSelect=new DataListSelect(listValues,selectedCallback);
        ComponentPopupBuilder componentPopupBuilder = JBPopupFactory.getInstance().createComponentPopupBuilder(dataListSelect, null);
        componentPopupBuilder.setProject(G.getProject());
        componentPopupBuilder.setMinSize(new Dimension(200,400));
        JBPopup jbPopup = componentPopupBuilder.createPopup();
        jbPopup.showInBestPositionFor(e.getDataContext());
        dataListSelect.setJbPopup(jbPopup);
    }
}
