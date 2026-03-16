package com.localbrief.ui;

import com.localbrief.model.AssetPageData;
import com.localbrief.model.EventItem;
import com.localbrief.model.KeyMetric;
import com.localbrief.service.DemoAssetPageService;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class AssetPageFrame extends JFrame {
    private static final Color BACKGROUND = new Color(10, 14, 24);
    private static final Color PANEL = new Color(17, 24, 39);
    private static final Color PANEL_ALT = new Color(13, 19, 31);
    private static final Color BORDER = new Color(35, 47, 68);
    private static final Color TEXT = new Color(231, 236, 245);
    private static final Color MUTED = new Color(139, 153, 175);
    private static final Color ACCENT = new Color(48, 213, 200);
    private static final Color ACCENT_FILL = new Color(48, 213, 200, 38);
    private static final Color POSITIVE = new Color(113, 255, 176);
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

    private final DemoAssetPageService assetPageService;
    private final JLabel modeLabel = new JLabel();
    private final JLabel titleLabel = new JLabel();
    private final JLabel subtitleLabel = new JLabel();
    private final JLabel priceLabel = new JLabel();
    private final JLabel changeLabel = new JLabel();
    private final JLabel statusLabel = new JLabel();
    private final JPanel chartHost = new JPanel(new BorderLayout());
    private final JPanel metricsGrid = new JPanel(new GridLayout(0, 3, 14, 14));
    private final JPanel sidebarStack = new JPanel();
    private final JPanel eventsStack = new JPanel();
    private final JPanel analysisStack = new JPanel();
    private final JTextArea summaryArea = new JTextArea();

    public AssetPageFrame(DemoAssetPageService assetPageService) {
        this.assetPageService = assetPageService;
        configureFrame();
        configureContent();
    }

    public void loadPage() {
        render(assetPageService.loadPage());
    }

    private void configureFrame() {
        setTitle("Local Data Brief");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1360, 860));
        setSize(1480, 940);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND);
    }

    private void configureContent() {
        JPanel root = new JPanel(new BorderLayout(18, 18));
        root.setBackground(BACKGROUND);
        root.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildBody(), BorderLayout.CENTER);

        setContentPane(root);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout(18, 18));
        header.setOpaque(false);

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        modeLabel.setOpaque(true);
        modeLabel.setBackground(new Color(22, 34, 53));
        modeLabel.setForeground(ACCENT);
        modeLabel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        modeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        modeLabel.setAlignmentX(LEFT_ALIGNMENT);

        titleLabel.setForeground(TEXT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);

        subtitleLabel.setForeground(MUTED);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setAlignmentX(LEFT_ALIGNMENT);

        left.add(modeLabel);
        left.add(Box.createVerticalStrut(14));
        left.add(titleLabel);
        left.add(Box.createVerticalStrut(6));
        left.add(subtitleLabel);

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        priceLabel.setForeground(TEXT);
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        priceLabel.setAlignmentX(RIGHT_ALIGNMENT);

        changeLabel.setForeground(POSITIVE);
        changeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        changeLabel.setAlignmentX(RIGHT_ALIGNMENT);

        statusLabel.setForeground(MUTED);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setAlignmentX(RIGHT_ALIGNMENT);

        JButton refreshButton = new JButton("Recharger la demo");
        refreshButton.setFocusable(false);
        refreshButton.setBackground(new Color(24, 37, 58));
        refreshButton.setForeground(TEXT);
        refreshButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        refreshButton.setAlignmentX(RIGHT_ALIGNMENT);
        refreshButton.addActionListener(event -> loadPage());

        right.add(priceLabel);
        right.add(Box.createVerticalStrut(4));
        right.add(changeLabel);
        right.add(Box.createVerticalStrut(10));
        right.add(statusLabel);
        right.add(Box.createVerticalStrut(12));
        right.add(refreshButton);

        header.add(left, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private JScrollPane buildBody() {
        JPanel body = new JPanel(new BorderLayout(18, 18));
        body.setOpaque(false);

        JPanel mainColumn = new JPanel();
        mainColumn.setOpaque(false);
        mainColumn.setLayout(new BoxLayout(mainColumn, BoxLayout.Y_AXIS));
        mainColumn.add(buildChartCard());
        mainColumn.add(Box.createVerticalStrut(18));
        mainColumn.add(buildMetricsCard());
        mainColumn.add(Box.createVerticalStrut(18));
        mainColumn.add(buildNarrativeCard());
        mainColumn.add(Box.createVerticalStrut(18));
        mainColumn.add(buildEventsCard());

        sidebarStack.setOpaque(false);
        sidebarStack.setLayout(new BoxLayout(sidebarStack, BoxLayout.Y_AXIS));

        JPanel sidebarContainer = new JPanel(new BorderLayout());
        sidebarContainer.setOpaque(false);
        sidebarContainer.setPreferredSize(new Dimension(330, 100));
        sidebarContainer.add(sidebarStack, BorderLayout.NORTH);

        body.add(mainColumn, BorderLayout.CENTER);
        body.add(sidebarContainer, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(body);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BACKGROUND);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private JPanel buildChartCard() {
        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 14));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = sectionTitle("Chart principal");
        JLabel subtitle = sectionHint("Vue demo locale orientee page actif, sans provider live.");

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(title);
        left.add(Box.createVerticalStrut(6));
        left.add(subtitle);

        header.add(left, BorderLayout.WEST);

        chartHost.setOpaque(false);
        card.add(header, BorderLayout.NORTH);
        card.add(chartHost, BorderLayout.CENTER);
        return card;
    }

    private JPanel buildMetricsCard() {
        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 16));

        JLabel title = sectionTitle("Metriques cles");
        card.add(title, BorderLayout.NORTH);

        metricsGrid.setOpaque(false);
        card.add(metricsGrid, BorderLayout.CENTER);
        return card;
    }

    private JPanel buildNarrativeCard() {
        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 16));

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(sectionTitle("Resume et narratif"));
        top.add(Box.createVerticalStrut(6));
        top.add(sectionHint("Bloc de lecture produit, pas un moteur de decision trading."));

        summaryArea.setEditable(false);
        summaryArea.setOpaque(false);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setForeground(TEXT);
        summaryArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        summaryArea.setBorder(null);

        analysisStack.setOpaque(false);
        analysisStack.setLayout(new BoxLayout(analysisStack, BoxLayout.Y_AXIS));

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(summaryArea);
        content.add(Box.createVerticalStrut(18));
        content.add(sectionTitle("Analyse simple"));
        content.add(Box.createVerticalStrut(12));
        content.add(analysisStack);

        card.add(top, BorderLayout.NORTH);
        card.add(content, BorderLayout.CENTER);
        return card;
    }

    private JPanel buildEventsCard() {
        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 16));
        card.add(sectionTitle("Evenements et lecture du contexte"), BorderLayout.NORTH);

        eventsStack.setOpaque(false);
        eventsStack.setLayout(new BoxLayout(eventsStack, BoxLayout.Y_AXIS));
        card.add(eventsStack, BorderLayout.CENTER);
        return card;
    }

    private void render(AssetPageData page) {
        modeLabel.setText(page.modeLabel());
        titleLabel.setText(page.asset().symbol() + "  " + page.asset().name());
        subtitleLabel.setText(page.asset().type() + "  •  " + page.asset().pair() + "  •  " + page.asset().venue());
        priceLabel.setText("$" + page.spotPrice().toPlainString());
        changeLabel.setText((page.change24hPercent().signum() >= 0 ? "+" : "") + page.change24hPercent() + "%  •  " + page.priceContext());
        statusLabel.setText(page.sourceLabel() + "  •  Mis a jour le " + DATE_TIME_FORMATTER.format(page.updatedAt()));

        chartHost.removeAll();
        chartHost.add(new AssetChartPanel(
                page.chartPoints(),
                PANEL_ALT,
                BORDER,
                ACCENT,
                ACCENT_FILL,
                TEXT,
                MUTED
        ), BorderLayout.CENTER);

        metricsGrid.removeAll();
        for (KeyMetric metric : page.keyMetrics()) {
            metricsGrid.add(new MetricTilePanel(metric, PANEL_ALT, BORDER, TEXT, MUTED));
        }

        summaryArea.setText(page.summary());

        analysisStack.removeAll();
        for (String point : page.analysisPoints()) {
            analysisStack.add(buildBulletPoint(point));
            analysisStack.add(Box.createVerticalStrut(10));
        }

        eventsStack.removeAll();
        for (EventItem event : page.events()) {
            eventsStack.add(buildEventCard(event));
            eventsStack.add(Box.createVerticalStrut(12));
        }

        sidebarStack.removeAll();
        sidebarStack.add(buildSidebarCard("Session locale", List.of(
                new KeyMetric("Mode", page.modeLabel(), "Le produit assume explicitement son statut demo."),
                new KeyMetric("Actif demo", page.asset().symbol(), "La priorite crypto est visible des cette etape."),
                new KeyMetric("Etat data", "Offline/mock", "Aucune source live branchee a ce stade.")
        )));
        sidebarStack.add(Box.createVerticalStrut(18));
        sidebarStack.add(buildSidebarCard("Colonne laterale", page.sidebarMetrics()));

        chartHost.revalidate();
        metricsGrid.revalidate();
        sidebarStack.revalidate();
        eventsStack.revalidate();
        analysisStack.revalidate();
        repaint();
    }

    private JPanel buildSidebarCard(String title, List<KeyMetric> metrics) {
        JPanel card = createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(320, 100));

        JLabel titleLabel = sectionTitle(title);
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(14));

        for (KeyMetric metric : metrics) {
            JLabel label = new JLabel(metric.label().toUpperCase());
            label.setAlignmentX(LEFT_ALIGNMENT);
            label.setForeground(MUTED);
            label.setFont(new Font("SansSerif", Font.BOLD, 11));

            JLabel value = new JLabel(metric.value());
            value.setAlignmentX(LEFT_ALIGNMENT);
            value.setForeground(TEXT);
            value.setFont(new Font("SansSerif", Font.BOLD, 18));

            JLabel detail = new JLabel("<html><body style='width:240px'>" + metric.detail() + "</body></html>");
            detail.setAlignmentX(LEFT_ALIGNMENT);
            detail.setForeground(MUTED);
            detail.setFont(new Font("SansSerif", Font.PLAIN, 12));

            card.add(label);
            card.add(Box.createVerticalStrut(6));
            card.add(value);
            card.add(Box.createVerticalStrut(6));
            card.add(detail);
            card.add(Box.createVerticalStrut(14));
        }
        return card;
    }

    private JPanel buildEventCard(EventItem event) {
        JPanel card = new JPanel();
        card.setOpaque(true);
        card.setBackground(PANEL_ALT);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel timeframe = new JLabel(event.timeframe().toUpperCase());
        timeframe.setAlignmentX(LEFT_ALIGNMENT);
        timeframe.setForeground(ACCENT);
        timeframe.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel title = new JLabel(event.title());
        title.setAlignmentX(LEFT_ALIGNMENT);
        title.setForeground(TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel detail = new JLabel("<html><body style='width:760px'>" + event.detail() + "</body></html>");
        detail.setAlignmentX(LEFT_ALIGNMENT);
        detail.setForeground(MUTED);
        detail.setFont(new Font("SansSerif", Font.PLAIN, 13));

        card.add(timeframe);
        card.add(Box.createVerticalStrut(8));
        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(detail);
        return card;
    }

    private JPanel buildBulletPoint(String text) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);

        JLabel bullet = new JLabel("•");
        bullet.setForeground(ACCENT);
        bullet.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel content = new JLabel("<html><body style='width:760px'>" + text + "</body></html>");
        content.setForeground(TEXT);
        content.setFont(new Font("SansSerif", Font.PLAIN, 14));

        row.add(bullet, BorderLayout.WEST);
        row.add(content, BorderLayout.CENTER);
        return row;
    }

    private JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        return panel;
    }

    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        return label;
    }

    private JLabel sectionHint(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(MUTED);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return label;
    }
}
