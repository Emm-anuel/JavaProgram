package com.epam.javaprogram.multithreading.task3;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Directory {

    private static class Task extends RecursiveAction {

        private static final long serialVersionUID = -196522408291343951L;

        private final File file;
        private final Folder folder;

        public Task(File file, Folder folder) {
            this.file = Objects.requireNonNull(file);
            this.folder = Objects.requireNonNull(folder);
        }

        @Override
        protected void compute() {
            if (file.isFile()) {
                folder.setSize(folder.getSize() + file.length());
                folder.setNumberFile(folder.getNumberFile() + 1);
            } else {
                folder.setNumberFolder(folder.getNumberFolder() + 1);
                final File[] children = file.listFiles();
                if (children != null) {
                    for (final File child : children) {
                        ForkJoinTask.invokeAll(new Task(child, folder));
                    }
                }
            }
        }
    }

    public static Folder detailsOf(File file) {
        final ForkJoinPool pool = new ForkJoinPool();
        try {
            Folder folder = new Folder();
            pool.invoke(new Task(file, folder));
            return folder;
        } finally {
            pool.shutdown();
        }
    }

    private Directory() {}

}