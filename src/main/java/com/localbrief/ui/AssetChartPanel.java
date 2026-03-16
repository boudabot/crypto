package com.localbrief.ui;

import com.localbrief.model.TimeSeriesPoint;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class AssetChartPanel extends JPanel {
    private static final DateTimeFormatter LABEL_FORMATTER = DateTimeFormatter.ofPattern("dd MMM");
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("$#,##0");

    private final List<TimeSeriesPoint> points;
    private final Color background;
    private final Color grid;
    private final Color accent;
    private final Color fill;
    private final Color text;
    private final Color muted;

    public AssetChartPanel(
            List<TimeSeriesPoint> points,
            Color background,
            Color grid,
            Color accent,
            Color fill,
            Color text,
            Color muted
    ) {
        this.points = points;
        this.background = background;
        this.grid = grid;
        this.accent = accent;
        this.fill = fill;
        this.text = text;
        this.muted = muted;
        setOpaque(true);
        setBackground(background);
        setPreferredSize(new Dimension(820, 360));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (points == null || points.size() < 2) {
            return;
        }

        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int left = 28;
        int right = width - 28;
        int top = 24;
        int bottom = height - 44;

        double min = points.stream().map(TimeSeriesPoint::value).mapToDouble(BigDecimal::doubleValue).min().orElse(0);
        double max = points.stream().map(TimeSeriesPoint::value).mapToDouble(BigDecimal::doubleValue).max().orElse(0);
        double range = Math.max(1, max - min);

        g2.setColor(grid);
        for (int i = 0; i < 4; i++) {
            int y = top + ((bottom - top) * i / 3);
            g2.drawLine(left, y, right, y);
        }

        Path2D line = new Path2D.Double();
        Path2D area = new Path2D.Double();
        for (int i = 0; i < points.size(); i++) {
            double x = left + ((right - left) * i / (double) (points.size() - 1));
            double normalized = (points.get(i).value().doubleValue() - min) / range;
            double y = bottom - ((bottom - top) * normalized);

            if (i == 0) {
                line.moveTo(x, y);
                area.moveTo(x, bottom);
                area.lineTo(x, y);
            } else {
                line.lineTo(x, y);
                area.lineTo(x, y);
            }
        }

        area.lineTo(right, bottom);
        area.closePath();

        g2.setColor(fill);
        g2.fill(area);

        g2.setColor(accent);
        g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(line);

        TimeSeriesPoint last = points.get(points.size() - 1);
        double lastX = right;
        double lastY = bottom - ((bottom - top) * ((last.value().doubleValue() - min) / range));
        g2.fillOval((int) lastX - 5, (int) lastY - 5, 10, 10);

        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2.setColor(muted);
        g2.drawString(points.get(0).time().format(LABEL_FORMATTER), left, height - 16);
        g2.drawString(points.get(points.size() - 1).time().format(LABEL_FORMATTER), right - 44, height - 16);

        g2.setColor(text);
        g2.setFont(new Font("SansSerif", Font.BOLD, 12));
        g2.drawString(PRICE_FORMAT.format(max), left, top - 6);
        g2.drawString(PRICE_FORMAT.format(min), left, bottom + 16);

        g2.dispose();
    }
}
