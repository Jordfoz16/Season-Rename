import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JScrollPane;

public class GUI {

	JFrame frmSeasonRenamer;
	JTextField txtPathToFolder;
	JTextField txtSeriesName;
	JTextField txtSubtitleLanguage;
	JTree fileTree;
	JButton btnScanFolder;
	JButton btnRename;
	JSpinner cntSeasonNumber;
	private final Action action = new ScanAction();
	
	Source source = new Source();
	private final Action action_1 = new RenameAction();
	
	public GUI() {
		initialize();
	}
	
	/*source.parseFolder("Nothing");
	source.correctTitle("This is a Test", 1, "English");
	source.rename();*/
	
	public void scanFile() {
		source.clear();
		source.parseFolder(txtPathToFolder.getText());
		
		fileTree.removeAll();
		
		fileTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Folder Path") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Video");
					for(int i = 0; i < source.videoFiles.size(); i++) {
						String title = source.videoFiles.get(i).getFilename();
						node_1.add(new DefaultMutableTreeNode(title));
					}
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Subtitle");
					for(int i = 0; i < source.subtitleFiles.size(); i++) {
						String title = source.subtitleFiles.get(i).getFilename();
						node_1.add(new DefaultMutableTreeNode(title));
					}
					add(node_1);
				}
			}
		));
		
		for(int i = 0; i < 1000; i++) {
			fileTree.expandRow(i);
			
		}
		
		fileTree.scrollRowToVisible(10);
	}
	
	public void renameFile() {
		
		if(txtSubtitleLanguage.getText() != "") {
			source.correctTitle(txtSeriesName.getText(), (int) cntSeasonNumber.getValue(), txtSubtitleLanguage.getText());
		}else {
			source.correctTitle(txtSeriesName.getText(), (int) cntSeasonNumber.getValue());
		}
		
		source.rename();
		scanFile();
	}

	private void initialize() {
		frmSeasonRenamer = new JFrame();
		frmSeasonRenamer.setTitle("Season Renamer");
		frmSeasonRenamer.setBounds(100, 100, 458, 601);
		frmSeasonRenamer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmSeasonRenamer.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtPathToFolder = new JTextField();
		txtPathToFolder.setToolTipText("Folder Path");
		txtPathToFolder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPathToFolder.setBounds(128, 23, 300, 28);
		panel.add(txtPathToFolder);
		txtPathToFolder.setColumns(10);
		
		JLabel lblFolderPath = new JLabel("Folder Path");
		lblFolderPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFolderPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFolderPath.setBounds(46, 28, 72, 14);
		panel.add(lblFolderPath);
		
		JLabel lblSeriesName = new JLabel("Series Name");
		lblSeriesName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeriesName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeriesName.setBounds(24, 67, 94, 14);
		panel.add(lblSeriesName);
		
		txtSeriesName = new JTextField();
		txtSeriesName.setToolTipText("Series Name");
		txtSeriesName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSeriesName.setColumns(10);
		txtSeriesName.setBounds(128, 62, 163, 28);
		panel.add(txtSeriesName);
		
		JLabel lblSeasonNumber = new JLabel("Season Number");
		lblSeasonNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeasonNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeasonNumber.setBounds(0, 106, 118, 14);
		panel.add(lblSeasonNumber);
		
		cntSeasonNumber = new JSpinner();
		cntSeasonNumber.setToolTipText("Season Number");
		cntSeasonNumber.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		cntSeasonNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cntSeasonNumber.setBounds(128, 101, 163, 28);
		panel.add(cntSeasonNumber);
		
		txtSubtitleLanguage = new JTextField();
		txtSubtitleLanguage.setToolTipText("Subtitle language");
		txtSubtitleLanguage.setText("English");
		txtSubtitleLanguage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSubtitleLanguage.setColumns(10);
		txtSubtitleLanguage.setBounds(128, 140, 163, 28);
		panel.add(txtSubtitleLanguage);
		
		JLabel lblSubtitleLanguage = new JLabel("Subtitle language");
		lblSubtitleLanguage.setToolTipText("Subtitle language");
		lblSubtitleLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtitleLanguage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubtitleLanguage.setBounds(0, 145, 118, 14);
		panel.add(lblSubtitleLanguage);
		
		btnScanFolder = new JButton("Scan Folder");
		btnScanFolder.setAction(action);
		btnScanFolder.setBounds(301, 62, 127, 28);
		panel.add(btnScanFolder);
		
		btnRename = new JButton("Rename");
		btnRename.setAction(action_1);
		btnRename.setBounds(301, 101, 127, 28);
		panel.add(btnRename);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 184, 422, 367);
		panel.add(scrollPane);
		
		fileTree = new JTree();
		scrollPane.setViewportView(fileTree);
		fileTree.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		fileTree.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Folder Path") {
				{
				}
			}
		));
	}
	
	private class ScanAction extends AbstractAction {
		public ScanAction() {
			putValue(NAME, "Scan Folder");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			scanFile();
		}
	}
	
	private class RenameAction extends AbstractAction {
		public RenameAction() {
			putValue(NAME, "Rename");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			renameFile();
		}
	}
	
	public static void main(String arg[]){
		
		
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				GUI window = new GUI();
				window.frmSeasonRenamer.setVisible(true);	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
	}
}
