/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import timetable.helper.JOptionPaneHelper;
import timetable.helper.TextHelper;
import timetable.manager.DatabaseManager;
import timetable.model.Subject;


public class AddSubject extends javax.swing.JDialog {

    private final DefaultListModel<Subject> subjectsListModel;
    private boolean refreshrequired;

    /**
     * Creates new form AddSubject
     *
     * @param parent
     * @param modal
     */
    public AddSubject(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.subjectsListModel = new DefaultListModel<>();
        this.subjectsList.setModel(subjectsListModel);
        this.subjectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        refreshSubjectLists();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                if (actionListener != null && refreshrequired) {
                    actionListener.refresh();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        subjectsList = new javax.swing.JList<>();
        addSubjectButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        subjectNameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        subjectsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subjectsListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(subjectsList);

        addSubjectButton.setText("Add");
        addSubjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubjectButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Subject Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subjectNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addSubjectButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSubjectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subjectsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectsListMouseClicked
        if (evt.getClickCount() == 2) {
            int index = subjectsList.locationToIndex(evt.getPoint());
            int confirm = JOptionPaneHelper.showConfirmationMessage(this, "Are you sure you want to remove this subject?", "Confirm");
            if (confirm == 0) {
                refreshrequired = true;
                DatabaseManager.getInstance().removeSubject(subjectsListModel.get(index).getSubjectID());
                refreshSubjectLists();
            }
        }
    }//GEN-LAST:event_subjectsListMouseClicked

    private void addSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubjectButtonActionPerformed
        String subjectName = this.subjectNameField.getText();
        if (!TextHelper.isEmpty(subjectName)) {
            int size = this.subjectsListModel.size();
            boolean canSubjectBeAdded = true;
            for (int i = 0; i < size; i++) {
                Subject currentSubject = this.subjectsListModel.getElementAt(i);
                if (TextHelper.isEqual(subjectName, currentSubject.toString())) {
                    canSubjectBeAdded = false;
                    break;
                }
            }
            if (canSubjectBeAdded) {
                DatabaseManager.getInstance().saveSubject(subjectName);
                this.subjectNameField.setText(null);
                refreshrequired = true;
                refreshSubjectLists();
            } else {
                JOptionPaneHelper.showWarningMessage(this, "Subject already exists on the list", "Info");
            }
        } else {
            JOptionPaneHelper.showWarningMessage(this, "Subject name should not be empty", "Info");
        }
    }//GEN-LAST:event_addSubjectButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSubjectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField subjectNameField;
    private javax.swing.JList<Subject> subjectsList;
    // End of variables declaration//GEN-END:variables

    /**
     * This method is used to populate subjects on the list
     */
    private void refreshSubjectLists() {
        List<Subject> allSubjects = DatabaseManager.getInstance().getAllSubjects();
        if (allSubjects != null) {
            this.subjectsListModel.clear();
            for (int i = 0; i < allSubjects.size(); i++) {
                this.subjectsListModel.addElement(allSubjects.get(i));
            }
        }
    }

    private OnSubjectActionListener actionListener;

    public void setActionListener(OnSubjectActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public interface OnSubjectActionListener {

        void refresh();
    }
}
