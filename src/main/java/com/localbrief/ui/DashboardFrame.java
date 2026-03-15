package com.localbrief.ui;

import com.localbrief.model.IndicatorSnapshot;
import com.localbrief.model.MarketOverview;
import com.localbrief.service.MarketOverviewService;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class DashboardFrame extends JFrame {
    private static final Color BACKGROUND = new Color(245, 243, 238);
    private static final Color PANEL = new Color(255, 252, 246);
    private static final Color TEXT = new Color(32, 28, 22);
    private static final Color ACCENT = new Color(26, 93, 79);
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

    private final MarketOverviewService overviewService;
    private final JTextArea narrativeArea = new JTextArea();
    private final JLabel statusLabel = new JLabel("Chargement...");
    private final JButton refreshButton = new JButton("Actualiser");
    private final JPanel cardsPanel = new JPanel();
    private final List<IndicatorCardPanel> cardPanels = new ArrayList<>();

    public DashboardFrame(MarketOverviewService overviewService) {
        this.overviewService = overviewService;
        configureLookAndFeel();
        configureFrame();
        configureContent();
    }

    public void loadData() {
        refreshButton.setEnabled(false);
        statusLabel.setText("Recuperation des donnees...");

        new SwingWorker<MarketOverview, Void>() {
            @Override
            protected MarketOverview doInBackground() throws Exception {
                return overviewService.fetchOverview();
            }

            @Override
            protected void done() {
                refreshButton.setEnabled(true);
                try {
                    render(get());
                } catch (Exception exception) {
                    statusLabel.setText("Erreur de chargement");
                    narrativeArea.setText("Impossible de recuperer les donnees.\n\n"
                            + exception.getMessage()
                            + "\n\nVerifie la connexion reseau puis relance `run.bat`.");
                }
            }
        }.execute();
    }

    private void configureLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    private void configureFrame() {
        setTitle("Local Data Brief");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 760));
        setSize(1280, 860);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND);
    }

    private void configureContent() {
        JPanel root = new JPanel(new BorderLayout(18, 18));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setBackground(BACKGROUND);

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildCenter(), BorderLayout.CENTER);

        setContentPane(root);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout(12, 12));
        header.setBackground(BACKGROUND);

        JLabel title = new JLabel("Local Data Brief");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel("V1 locale gratuite en Java pour suivre des donnees publiques.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitle.setForeground(new Color(80, 76, 70));

        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setOpaque(false);
        titleBlock.add(title);
        titleBlock.add(Box.createVerticalStrut(4));
        titleBlock.add(subtitle);

        refreshButton.addActionListener(event -> loadData());
        refreshButton.setFocusable(false);

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        statusLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        refreshButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        right.add(statusLabel);
        right.add(Box.createVerticalStrut(8));
        right.add(refreshButton);

        header.add(titleBlock, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private JScrollPane buildCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);

        narrativeArea.setWrapStyleWord(true);
        narrativeArea.setLineWrap(true);
        narrativeArea.setEditable(false);
        narrativeArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        narrativeArea.setForeground(TEXT);
        narrativeArea.setBackground(PANEL);
        narrativeArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 214, 201), 1),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        cardsPanel.setOpaque(false);
        cardsPanel.setLayout(new java.awt.GridLayout(0, 2, 18, 18));

        center.add(narrativeArea);
        center.add(Box.createVerticalStrut(18));
        center.add(cardsPanel);

        JScrollPane scrollPane = new JScrollPane(center);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BACKGROUND);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private void render(MarketOverview overview) {
        narrativeArea.setText(overview.narrative());
        statusLabel.setText(overview.sourceLabel() + " - " + DATE_TIME_FORMATTER.format(overview.fetchedAt()));

        cardsPanel.removeAll();
        cardPanels.clear();
        for (IndicatorSnapshot indicator : overview.indicators()) {
            IndicatorCardPanel card = new IndicatorCardPanel(indicator, PANEL, TEXT, ACCENT);
            cardPanels.add(card);
            cardsPanel.add(card);
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }
}
