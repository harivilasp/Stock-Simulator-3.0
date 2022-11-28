package view;

import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import controller.Features;

public class JPerfGraphView extends JPanel implements PanelView {

  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JTextField startDateField;
  private JTextField endDateField;
  private JButton showButton;
  private JButton homeButton;
  private JPanel southPanel;
  private JPanel centerPanel;

  public JPerfGraphView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    // North panel -> Title
    this.titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>"); // TODO

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    northPanel.add(this.titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Text input fields
    this.startDateField = new JTextField(10);
    this.endDateField = new JTextField(10);
    this.showButton = new JButton("SHOW");

    centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
    centerPanel.add(new JLabel("Start date (yyyy-mm-dd): "));
    centerPanel.add(this.startDateField);
    centerPanel.add(new JLabel("End date (yyyy-mm-dd): "));
    centerPanel.add(this.endDateField);
    centerPanel.add(this.showButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Home button
    this.homeButton = new JButton("HOME");
    this.homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    southPanel = new JPanel();
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
    southPanel.add(this.homeButton);
    southPanel.add(new JLabel("      "));
    this.add(southPanel, BorderLayout.SOUTH);
  }

  public void setPortfolioName(String portfolioName) {
    portfolioLabel.setText(portfolioName);
  }

  private DefaultCategoryDataset createDataset() {
    String series1 = "Visitor";

    DefaultCategoryDataset newdataset = new DefaultCategoryDataset();

    newdataset.addValue(200, series1, "2016-12-19");
    newdataset.addValue(150, series1, "2016-12-20");
    newdataset.addValue(100, series1, "2016-12-21");
    newdataset.addValue(210, series1, "2016-12-22");
    newdataset.addValue(240, series1, "2016-12-23");
    newdataset.addValue(195, series1, "2016-12-24");
    newdataset.addValue(245, series1, "2016-12-25");

    return newdataset;
  }

  @Override
  public void addActionListener(Features features) {
    showButton.addActionListener(event -> {
      try {
        Map<String, Integer> map
                = features.getPerformance(startDateField.getText(), endDateField.getText());
        PerformanceGraph.DrawGraph(map);
      } catch (Exception e) {
        e.getMessage();
      }
    });
    homeButton.addActionListener(event -> {
      features.showHome();
    });
  }

  @Override
  public void clearInput() {

  }
}
