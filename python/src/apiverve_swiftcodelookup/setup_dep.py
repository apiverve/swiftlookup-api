from setuptools import setup, find_packages

setup(
    name='apiverve_swiftcodelookup',
    version='1.2.2',
    packages=find_packages(),
    include_package_data=True,
    install_requires=[
        'requests',
        'setuptools'
    ],
    description='SWIFT Code Lookup identifies the bank institution, branch, city, and country code behind any SWIFT or BIC code. Search by code or query by bank name to verify matching financial institutions and branch locations.',
    author='APIVerve',
    author_email='hello@apiverve.com',
    url='https://apiverve.com/marketplace/swiftlookup?utm_source=pypi&utm_medium=referral&utm_content=homepage',
    classifiers=[
        'Programming Language :: Python :: 3',
        'Operating System :: OS Independent',
    ],
    python_requires='>=3.6',
)
