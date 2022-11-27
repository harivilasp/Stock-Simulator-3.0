package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JLoadPortfolioView extends JPanel {

  private JButton homeButton;
  private JLabel titleLabel;
  private JButton chooseFileButton;
  private JLabel messageLabel;

  public JLoadPortfolioView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    // North panel -> Title
    this.titleLabel = new JLabel(title);

    JPanel northPanel = new JPanel();
    northPanel.add(this.titleLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Choose file button
    this.chooseFileButton = new JButton("Choose portfolio from filepath");
    this.homeButton = new JButton("HOME");

    this.chooseFileButton.addActionListener(event -> {
      // Open file chooser
      JFileChooser fileChooser = new JFileChooser();
      // Apply file extension filters
      FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
          "Text files",
          "txt"
      );
      fileChooser.setFileFilter(fileFilter);

      // Retrieve response code from the file chooser
      int responseCode = fileChooser.showOpenDialog(JLoadPortfolioView.this);
      // Response code is valid
      if (responseCode == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        this.messageLabel.setText(selectedFile.getAbsolutePath()); // TODO
      }
    });

    JPanel centerPanel = new JPanel();
    centerPanel.add(this.chooseFileButton);
    centerPanel.add(this.homeButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Message to show whether portfolio loaded was valid or not.
    this.messageLabel = new JLabel("<Message comes here>"); // TODO

    JPanel southPanel = new JPanel();
    southPanel.add(this.messageLabel);
    this.add(southPanel, BorderLayout.SOUTH);
  }
}
